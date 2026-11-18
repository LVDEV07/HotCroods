package com.cantina.exceptions;

import java.math.BigInteger;

public class ProdutoInsuficienteException extends RuntimeException {
    public ProdutoInsuficienteException(String nome, BigInteger quantidade) {
        super("O Produto '" + nome+ "' possui somente " + quantidade + " itens disponíveis");
    }
}
