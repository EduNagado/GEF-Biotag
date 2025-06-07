package com.gef_biotag.Biotag.dto;

import jakarta.validation.constraints.NotBlank;

public record PacienteDTO(

    @NotBlank(message = "campo obrigatório")
    String nome,

    
    Integer idade,

    @NotBlank(message = "campo obrigatório")
    String endereco,

    @NotBlank(message = "campo obrigatório")
    String nomeAbrigo
){
}
