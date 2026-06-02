package com.empresa.pedidos.infraestructura.notificaciones;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.empresa.pedidos.dominio.eventos.PedidoProcesadoEvent;
import com.empresa.pedidos.dominio.puertos.ServicioNotificacion;

@Component
public class NotificacionEmail implements ServicioNotificacion {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificacionEmail.class);

    @EventListener
    @Override
    public void notificar(PedidoProcesadoEvent evento) {
        // Simulacion del envio; un adaptador SMTP real puede reemplazar este log.
        LOGGER.info("Email enviado para pedido: {}", evento.pedido().getId());
    }
}
