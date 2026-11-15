package com.cantina.dto;

import com.cantina.model.Pedido;

import java.util.List;

public record UsuarioRequestDTO(
        String nome,
        String cpf,
        String email,
        String senha

) {
}
