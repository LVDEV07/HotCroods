package com.cantina.dto;

import java.math.BigInteger;

public record ItemPedidoDTO(
        String nome,
        BigInteger quantidade
) {
}
