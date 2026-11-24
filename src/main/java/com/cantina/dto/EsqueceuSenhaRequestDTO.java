package com.cantina.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EsqueceuSenhaRequestDTO(

        @NotBlank(message = "O email é obrigatório")
        @Email(message = "Formato de email incorreto")
        String email,

        @NotBlank(message = "A nova senha é obrigatória")
        String novaSenha
) {
}