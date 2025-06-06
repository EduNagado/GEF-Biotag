package com.gef_biotag.Biotag.dto;

import com.gef_biotag.Biotag.model.Paciente;

public record DadosDetalhadoPaciente(String nome, Integer idade, String endereco) {
    public DadosDetalhadoPaciente(Paciente paciente) {
        this(paciente.getNome(), paciente.getIdade(), paciente.getEndereco());
    }
}