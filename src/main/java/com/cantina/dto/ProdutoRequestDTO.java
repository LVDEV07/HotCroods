package com.cantina.dto;

import com.cantina.model.CategoriaProduto;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.math.BigInteger;

public record ProdutoRequestDTO(

        @NotBlank(message = "O nome é obrigatório")
        @Size(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres")
        String nome,

        @NotNull(message = "O valor é obrigatório")
        @DecimalMin(value = "0.01", message = "O valor mínimo é R$ 0,01")
        @DecimalMax(value = "100.00", message = "O valor máximo é R$ 100,00")
        BigDecimal valor,

        @NotNull(message = "A quantidade é obrigatória")
        @Min(value = 0, message = "A quantidade não pode ser negativa")
        @Max(value = 100, message = "A quantidade máxima é 100")
        BigInteger quantidade,

        @NotNull(message = "É obrigatório ter uma categoria")
        CategoriaProduto categoriaProduto,

        @NotBlank
        String url,

        Boolean disponivel
) {
}