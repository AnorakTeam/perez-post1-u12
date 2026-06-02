package com.empresa.pedidos.infraestructura.persistencia;

import org.springframework.data.jpa.repository.JpaRepository;
import com.empresa.pedidos.dominio.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
