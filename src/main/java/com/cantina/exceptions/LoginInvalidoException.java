package com.cantina.exceptions;

public class LoginInvalidoException extends RuntimeException {
    public LoginInvalidoException() {
        super("E-mail ou senha inválidos");
    }
}
