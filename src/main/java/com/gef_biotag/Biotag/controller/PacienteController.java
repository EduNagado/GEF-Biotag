package com.gef_biotag.Biotag.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gef_biotag.Biotag.dto.DadosAtualizadoPaciente;
import com.gef_biotag.Biotag.dto.DadosDetalhadoPaciente;
import com.gef_biotag.Biotag.dto.DadosListandoPaciente;
import com.gef_biotag.Biotag.dto.PacienteDTO;
import com.gef_biotag.Biotag.model.Abrigo;
import com.gef_biotag.Biotag.model.Paciente;
import com.gef_biotag.Biotag.model.Pulseira;
import com.gef_biotag.Biotag.repository.AbrigoRepository;
import com.gef_biotag.Biotag.repository.PacienteRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private AbrigoRepository abrigoRepository;

    @Autowired  
    private PacienteRepository pacienteRepository;

    @Autowired
    private pulseiraService pulseiraService;

    @PostMapping
    @Operation(summary = "Cadastrar Paciente")
    public ResponseEntity<Paciente> create(@RequestBody @Valid PacienteDTO dto) {

        Abrigo abrigo = abrigoRepository.findByNome(dto.nomeAbrigo())
            .orElseThrow(() -> new IllegalArgumentException("Abrigo não encontrado"));

        Pulseira pulseira = pulseiraService.criarPulseira(80);

        Paciente paciente = new Paciente();
        paciente.setNome(dto.nome());
        paciente.setIdade(dto.idade());
        paciente.setEndereco(dto.endereco());
        paciente.setAbrigo(abrigo);
        paciente.setPulseira(pulseira);

        Paciente pacienteSalvo = pacienteRepository.save(paciente);

        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteSalvo);
    }

    @GetMapping
    @Operation(summary = "Lista os pacientes com paginação e filtros")
    public Page<Paciente> listar(
            @ParameterObject @PageableDefault(size = 10, sort = "nome") Pageable pageable,
            @RequestParam(required = false) String nome
    ) {
        log.info("Listando pacientes. Filtro nome = {}", nome);

        if (nome != null && !nome.isBlank()) {
            return pacienteRepository.findByNomeContainingIgnoreCase(nome, pageable);
        }

        return pacienteRepository.findAll(pageable);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<DadosListandoPaciente> buscarPorId(@PathVariable Long id) {
        Paciente paciente = pacienteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Paciente não encontrado com ID: " + id));
        return ResponseEntity.ok(new DadosListandoPaciente(paciente));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar informação do funcionário")
    @Transactional
    public ResponseEntity<DadosDetalhadoPaciente> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid DadosAtualizadoPaciente dadosPaciente) {
        Paciente paciente = pacienteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Funcionário não encontrado com ID: " + id));
        paciente.atualizarInformacoesPaciente(dadosPaciente);


        return ResponseEntity.ok(new DadosDetalhadoPaciente(paciente));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar Funcionario por ID")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id){
        pacienteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}