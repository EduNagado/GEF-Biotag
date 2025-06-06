package com.gef_biotag.Biotag.dto;

import com.gef_biotag.Biotag.model.BatimentoCardiaco;

public record BatimentoCardiacoDTO(Integer bpm, String timestamp) {
    public BatimentoCardiacoDTO(BatimentoCardiaco batimento) {
        this(
            batimento.getBpm(),
            batimento.getTimestamp() != null ? batimento.getTimestamp().toString() : null
        );
    }
}