package com.empresa.pedidos.dominio;

/** Identificador validado de un pedido persistido. */
public record PedidoId(Long valor) {

    public PedidoId {
        if (valor == null || valor <= 0) {
            throw new IllegalArgumentException("El identificador debe ser positivo");
        }
    }
}
