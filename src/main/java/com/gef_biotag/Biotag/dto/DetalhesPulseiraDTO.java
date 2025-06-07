package com.gef_biotag.Biotag.dto;

import java.time.LocalDateTime;

public record DetalhesPulseiraDTO(
    Long pulseiraId,
    String nomePaciente,
    Integer idade,
    String endereco,
    String nomeAbrigo,
    Long idNFC,
    Integer bpm,
    LocalDateTime timestamp
){}