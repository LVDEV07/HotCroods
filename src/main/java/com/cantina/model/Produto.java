package com.cantina.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String nome;


    @Column(nullable = false)



    private BigDecimal valor;

    @Column(nullable = false)
    private BigInteger quantidade;

    @Column (nullable = false)
    @Enumerated(EnumType.STRING)
    private CategoriaProduto categoria;

    private String url;

    private Boolean disponivel;

}
