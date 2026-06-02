package com.empresa.pedidos.infraestructura.notificaciones;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.empresa.pedidos.dominio.eventos.PedidoProcesadoEvent;
import com.empresa.pedidos.dominio.puertos.ServicioNotificacion;

@Component
public class NotificacionLog implements ServicioNotificacion {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificacionLog.class);

    @EventListener
    @Override
    public void notificar(PedidoProcesadoEvent evento) {
        LOGGER.info("Pedido procesado: {} - Costo: {}",
                evento.pedido().getId(), evento.pedido().getCosto());
    }
}
