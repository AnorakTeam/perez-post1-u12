package com.empresa.pedidos.dominio.puertos;

import com.empresa.pedidos.dominio.Pedido;
import com.empresa.pedidos.dominio.TipoPedido;

/** Strategy para calcular el costo y procesar un tipo de pedido. */
public interface ProcesadorPedido {

    /**
     * @return tipo de pedido atendido por esta estrategia.
     */
    TipoPedido getTipo();

    /**
     * Calcula costo y actualiza estado del pedido.
     *
     * @param pedido pedido por procesar.
     */
    void procesar(Pedido pedido);
}
