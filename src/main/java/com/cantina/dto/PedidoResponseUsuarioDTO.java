package com.cantina.dto;

import com.cantina.model.Produto;

import java.util.List;

public record PedidoResponseUsuarioDTO(
        Long id,
        List<Produto> produtos
) {
}
