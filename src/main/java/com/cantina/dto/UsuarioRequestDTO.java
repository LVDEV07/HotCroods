package com.cantina.dto;

import com.cantina.model.Pedido;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record UsuarioRequestDTO(

        @NotBlank(message = "O nome é obrigatório")
        String nome,

        @NotBlank(message = "O cpf é obrigatório")
        String cpf,

        @NotBlank(message = "O email é obrigatório")
        @Email(message = "Formato de email incorreto")
        String email,

        @NotBlank(message = "A senha é obrigatório")
        String senha

) {
}
