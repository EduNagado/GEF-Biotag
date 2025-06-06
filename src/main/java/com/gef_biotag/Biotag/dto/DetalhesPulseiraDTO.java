package com.gef_biotag.Biotag.dto;

import java.time.LocalDateTime;

public record DetalhesPulseiraDTO(
    Long idPulseira,
    String nomePaciente,
    Integer idade,
    String endereco,
    String nomeAbrigo,
    Long idNFC,
    Integer bpm,
    LocalDateTime timestamp
){}