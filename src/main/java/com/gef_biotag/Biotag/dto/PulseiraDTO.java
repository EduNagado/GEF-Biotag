package com.gef_biotag.Biotag.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PulseiraDTO(


    @NotNull(message = "BPM não pode ser nulo")
    Integer bpm,

    @NotBlank(message = "campo obrigatório")
    String timestamp){
    
}
