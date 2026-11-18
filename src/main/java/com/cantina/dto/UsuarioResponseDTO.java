package com.cantina.dto;

import com.cantina.model.Pedido;

import java.util.List;

public record UsuarioResponseDTO(
        Long id,
        String nome,
        String email,
        List<PedidoResponseUsuarioDTO> pedidos



) {
}
