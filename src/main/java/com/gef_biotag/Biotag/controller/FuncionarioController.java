package com.gef_biotag.Biotag.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gef_biotag.Biotag.dto.DadosAtualizadoFuncionario;
import com.gef_biotag.Biotag.dto.DadosDetalhadoFuncionario;
import com.gef_biotag.Biotag.dto.DadosTokenJWT;
import com.gef_biotag.Biotag.dto.FuncionarioDTO;
import com.gef_biotag.Biotag.dto.LoginDTO;
import com.gef_biotag.Biotag.model.Abrigo;
import com.gef_biotag.Biotag.model.Funcionario;
import com.gef_biotag.Biotag.repository.AbrigoRepository;
import com.gef_biotag.Biotag.repository.FuncionarioRepository;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {
    
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private AbrigoRepository abrigoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    @Operation(summary = "Cadastrar Funcionario")
    public ResponseEntity<Funcionario> create(@RequestBody @Valid FuncionarioDTO dto) {
        Abrigo abrigo = abrigoRepository.findByNome(dto.nomeAbrigo())
            .orElseThrow(() -> new IllegalArgumentException("Abrigo não encontrado"));

        Funcionario funcionario = new Funcionario();
        funcionario.setNome(dto.nome());
        funcionario.setPassword(dto.password());
        funcionario.setEmail(dto.email());
        funcionario.setCargo(dto.cargo());
        funcionario.setAbrigo(abrigo);

        String senhaCriptografada = passwordEncoder.encode(dto.password());
        funcionario.setPassword(senhaCriptografada);

        Funcionario funcionarioSalvo = funcionarioRepository.save(funcionario);
        return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioSalvo);
    }


    @PostMapping("/login")
        @Operation(summary = "Logar Funcionario")
    public ResponseEntity login(@RequestBody @Valid LoginDTO LoginDTO){
         var userPwd = new UsernamePasswordAuthenticationToken(
                LoginDTO.email(),
                LoginDTO.password());
        var auth = this.manager.authenticate(userPwd);
        var token = tokenService.generateToken((Funcionario) auth.getPrincipal());
        return ResponseEntity.ok(new DadosTokenJWT(token));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar informação do funcionário")
    @Transactional
    public ResponseEntity<DadosDetalhadoFuncionario> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid DadosAtualizadoFuncionario dados) {
        Funcionario funcionario = funcionarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Funcionário não encontrado com ID: " + id));
        funcionario.atualizarInformacoes(dados);

        String senhaCriptografada = passwordEncoder.encode(dados.password());
        funcionario.setPassword(senhaCriptografada);

        return ResponseEntity.ok(new DadosDetalhadoFuncionario(funcionario));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar Funcionario por ID")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id){
        if (!funcionarioRepository.existsById(id)) {
            throw new EntityNotFoundException("Funcionário com ID " + id + " não encontrado");
        }
        funcionarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
