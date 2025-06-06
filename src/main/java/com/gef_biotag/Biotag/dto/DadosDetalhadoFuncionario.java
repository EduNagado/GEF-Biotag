package com.gef_biotag.Biotag.dto;

import com.gef_biotag.Biotag.model.Cargo;
import com.gef_biotag.Biotag.model.Funcionario;

public record DadosDetalhadoFuncionario(String nome, String email, String password, Cargo cargo) {
    public DadosDetalhadoFuncionario(Funcionario funcionario) {
        this(funcionario.getNome(), funcionario.getEmail(), funcionario.getPassword(), funcionario.getCargo());
    }
}