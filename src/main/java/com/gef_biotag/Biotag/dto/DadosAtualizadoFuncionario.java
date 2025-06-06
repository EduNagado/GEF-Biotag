package com.gef_biotag.Biotag.dto;

import com.gef_biotag.Biotag.model.Cargo;

public record DadosAtualizadoFuncionario(
    String nome,
    String email,
    String password,
    Cargo cargo) {

}