package com.gef_biotag.Biotag.dto;

import com.gef_biotag.Biotag.model.Abrigo;


public record DadosDetalhadoAbrigo(String nome) {
    public DadosDetalhadoAbrigo(Abrigo abrigo) {
        this(abrigo.getNome());
    }
}
