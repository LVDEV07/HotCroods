package com.cantina.dto;

import com.cantina.model.Produto;

import java.util.List;

public record PedidoRequestDTO(
        List<Produto> produtos,
        Long usuarioId
) {
}
