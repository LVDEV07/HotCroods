package com.cantina.dto;

import java.math.BigDecimal;
import java.math.BigInteger;

public record ProdutoResponseDTO(Long id, String nome, BigDecimal valor, BigInteger quantidade){
}
