package com.cantina.exceptions;

public class ProdutoNotFoundException extends RuntimeException {
    public ProdutoNotFoundException(Long id) {

        super("Produto não encontrado com id: " + id);
    }

    public ProdutoNotFoundException(String nome) {

        super("Produto não encontrado com o nome: " + nome);
    }
}
