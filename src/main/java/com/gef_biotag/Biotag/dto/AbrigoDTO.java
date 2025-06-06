package com.gef_biotag.Biotag.dto;

import jakarta.validation.constraints.NotBlank;

public record AbrigoDTO(

    @NotBlank(message = "campo obrigatório")
    String nome,
    
    @NotBlank(message = "campo obrigatório")
    String endereco) {
    
}
