package com.cantina.dto;

import java.util.List;

public record PedidoRequestDTO(
        List<ItemPedidoDTO> produtos,
        Long usuarioId
) {
}
