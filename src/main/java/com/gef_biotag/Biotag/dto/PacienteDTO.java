package com.gef_biotag.Biotag.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PacienteDTO(

    @NotBlank(message = "campo obrigatório")
    String nome,

    @NotNull
    Integer idade,

    @NotBlank(message = "campo obrigatório")
    String endereco,

    @NotBlank(message = "campo obrigatório")
    String nomeAbrigo){
}
