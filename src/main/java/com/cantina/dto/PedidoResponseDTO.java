package com.cantina.dto;

import com.cantina.model.Produto;

import java.util.List;

public record PedidoResponseDTO(
        Long id,
        List<Produto> produtos,
        Long usuarioID
) {
}
