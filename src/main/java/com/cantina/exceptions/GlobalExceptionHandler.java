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


}
