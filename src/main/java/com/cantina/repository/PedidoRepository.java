package com.cantina.repository;

import com.cantina.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
