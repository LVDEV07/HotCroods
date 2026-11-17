package com.cantina.repository;

import com.cantina.model.CategoriaProduto;
import com.cantina.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    Optional<Produto> findByNome(String nome);
    Optional<Produto> findByCategoria(CategoriaProduto categoria);
}
