package com.cantina.exceptions;

public class UsuarioNotFoundException extends RuntimeException {
    public UsuarioNotFoundException(Long id) {
        super("Usuário não encontrado com o id: " + id);
    }

    public UsuarioNotFoundException(String email) {
        super("Usuário não encontrado com o email: " + email);
    }
}