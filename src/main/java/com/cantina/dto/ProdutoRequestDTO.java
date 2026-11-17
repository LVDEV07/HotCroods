package com.cantina.dto;

import com.cantina.model.CategoriaProduto;

import java.math.BigDecimal;
import java.math.BigInteger;

public record ProdutoRequestDTO(String nome, BigDecimal valor, BigInteger quantidade, CategoriaProduto categoriaProduto) {
}
