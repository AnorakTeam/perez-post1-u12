package com.empresa.pedidos.dominio.puertos;

import java.util.Optional;

import com.empresa.pedidos.dominio.Pedido;
import com.empresa.pedidos.dominio.PedidoId;

/** Puerto de persistencia consumido por la capa de aplicacion. */
public interface RepositorioPedidos {

    Pedido guardar(Pedido pedido);

    Optional<Pedido> buscarPorId(PedidoId id);
}
