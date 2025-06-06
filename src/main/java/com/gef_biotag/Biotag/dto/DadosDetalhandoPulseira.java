package com.gef_biotag.Biotag.dto;

import com.gef_biotag.Biotag.model.Pulseira;

public record DadosDetalhandoPulseira(NfcDTO nfc, BatimentoCardiacoDTO batimento) {
    public DadosDetalhandoPulseira(Pulseira pulseira) {
        this(
            new NfcDTO(pulseira.getNfc()),
            new BatimentoCardiacoDTO(pulseira.getBatimento())
        );
    }
}
