package com.gef_biotag.Biotag.dto;

import com.gef_biotag.Biotag.model.Paciente;

public record DadosListandoPaciente(
    String nome,
    Integer idade,
    String endereco,
    DadosDetalhadoAbrigo abrigo,
    DadosDetalhandoPulseira pulseira
) {
    public DadosListandoPaciente(Paciente paciente) {
        this(
            paciente.getNome(),
            paciente.getIdade(),
            paciente.getEndereco(),
            new DadosDetalhadoAbrigo(paciente.getAbrigo()),
            new DadosDetalhandoPulseira(paciente.getPulseira())
        );
    }
}