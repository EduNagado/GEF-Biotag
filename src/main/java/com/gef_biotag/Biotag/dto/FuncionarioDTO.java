package com.gef_biotag.Biotag.dto;

import com.gef_biotag.Biotag.model.Cargo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record FuncionarioDTO(

    @NotBlank(message = "campo obrigatório")
    String nome,

    @NotBlank(message = "campo obrigatório")
    @Size(min = 8, message = "A senha deve ter pelo menos 8 caracteres")
    String password,

    @NotNull(message = "campo obrigatório")
    Cargo cargo,

    @Email(message = "Email inválido")
    @NotBlank
    String email,

    @NotBlank(message = "campo obrigatório")
    String nomeAbrigo){
    
}
