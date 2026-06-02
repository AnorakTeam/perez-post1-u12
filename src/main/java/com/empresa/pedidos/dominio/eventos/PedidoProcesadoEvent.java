package com.empresa.pedidos.dominio.eventos;

import com.empresa.pedidos.dominio.Pedido;

/** Evento emitido cuando el pedido ya fue calculado y persistido. */
public record PedidoProcesadoEvent(Pedido pedido) {
}
