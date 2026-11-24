package com.cantina.dto;

import com.cantina.model.CategoriaProduto;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.math.BigInteger;

public record ProdutoRequestDTO(
        @NotBlank(message = "O nome é obrigatório")
        String nome,

        @Positive(message = "O valor deve ser positivo")
        BigDecimal valor,

        @PositiveOrZero(message = "O número deve ser 0 ou maior")
        BigInteger quantidade,

        @NotNull(message = "É obrigatório ter uma categoria")
        CategoriaProduto categoriaProduto,

        String url,

        Boolean disponivel

) {
}
