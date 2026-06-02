package com.empresa.pedidos.dominio.puertos;

import com.empresa.pedidos.dominio.eventos.PedidoProcesadoEvent;

/** Puerto Observer para reaccionar ante un pedido procesado. */
public interface ServicioNotificacion {

    void notificar(PedidoProcesadoEvent evento);
}
