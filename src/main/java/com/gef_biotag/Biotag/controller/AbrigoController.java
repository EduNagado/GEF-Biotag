package com.gef_biotag.Biotag.controller;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gef_biotag.Biotag.dto.AbrigoDTO;
import com.gef_biotag.Biotag.model.Abrigo;
import com.gef_biotag.Biotag.repository.AbrigoRepository;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/abrigos")
@Slf4j
public class AbrigoController {

    @Autowired
    private AbrigoRepository abrigoRepository;
     
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cadastra um novo abrigo")
    public Abrigo create(@RequestBody @Valid AbrigoDTO dto) {
        

        Abrigo abrigo = new Abrigo();
        abrigo.setNome(dto.nome());
        abrigo.setEndereco(dto.endereco());

        return abrigoRepository.save(abrigo);
    }



    @GetMapping
    @Operation(summary = "Lista abrigos com paginação")
    public Page<Abrigo> listar(
            @ParameterObject @PageableDefault(size = 10, sort = "nome") Pageable pageable
    ) {
        log.info("Listando abrigos");
        return abrigoRepository.findAll(pageable);
    }


}
