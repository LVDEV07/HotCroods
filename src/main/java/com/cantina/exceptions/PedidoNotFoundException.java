package com.cantina.exceptions;

public class PedidoNotFoundException extends RuntimeException {
    public PedidoNotFoundException(Long id) {
        super("Pedido não encontrado com o id: " + id);
    }
}
