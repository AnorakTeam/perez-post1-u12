package com.empresa.pedidos.infraestructura.notificaciones;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.empresa.pedidos.dominio.Pedido;
import com.empresa.pedidos.dominio.TipoPedido;
import com.empresa.pedidos.dominio.eventos.PedidoProcesadoEvent;

class NotificacionEmailTest {

    @Test
    void observerEmailReaccionaAlEventoSinAcoplarElCasoDeUso() {
        Pedido pedido = new Pedido(TipoPedido.ESTANDAR, new BigDecimal("25.00"));

        assertDoesNotThrow(() -> new NotificacionEmail().notificar(new PedidoProcesadoEvent(pedido)));
    }
}
