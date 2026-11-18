package com.cantina.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {

    @ExceptionHandler(UsuarioNotFoundException.class)
    public ResponseEntity<Map<String, String>> usuarioNotFound(UsuarioNotFoundException error){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("erro", error.getMessage()));
    }

    @ExceptionHandler(ProdutoNotFoundException.class)
    public ResponseEntity<Map<String, String>> produtoNotFound(ProdutoNotFoundException error){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("erro", error.getMessage()));
    }
    @ExceptionHandler(PedidoNotFoundException.class)
    public ResponseEntity<Map<String, String>> pedidoNotFound (PedidoNotFoundException error){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("erro", error.getMessage()));
    }
    @ExceptionHandler(LoginInvalidoException.class)
    public ResponseEntity<Map<String, String>> loginInvalido (LoginInvalidoException error){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("erro", error.getMessage()));
    }
    @ExceptionHandler(ProdutoInsuficienteException.class)
    public ResponseEntity<Map<String, String>> loginInvalido (ProdutoInsuficienteException error){
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_CONTENT)
                .body(Map.of("erro", error.getMessage()));
    }


}
