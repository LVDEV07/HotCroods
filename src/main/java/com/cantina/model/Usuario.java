package com.cantina.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String nome;


    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String senha;

    @OneToMany(mappedBy = "usuario")
    private List<Pedido> pedidos;






}
