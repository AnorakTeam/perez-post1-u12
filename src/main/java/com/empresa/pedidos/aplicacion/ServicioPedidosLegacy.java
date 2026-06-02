package com.empresa.pedidos.aplicacion;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.empresa.pedidos.dominio.EstadoPedido;
import com.empresa.pedidos.dominio.Pedido;
import com.empresa.pedidos.dominio.TipoPedido;
import com.empresa.pedidos.dominio.puertos.RepositorioPedidos;

/**
 * Baseline intencional: concentra calculo, persistencia y notificacion.
 * Compila, pero conserva los olores de diseno que deben observarse en Sonar.
 */
@Service
public class ServicioPedidosLegacy {

    @Autowired
    private RepositorioPedidos repo;

    @Autowired
    private JavaMailSender mail;

    public Pedido procesarPedido(Pedido pedido) {
        if (pedido.getTipo() == TipoPedido.ESTANDAR) {
            pedido.setCosto(pedido.getSubtotal().multiply(BigDecimal.valueOf(1.10)));
        } else if (pedido.getTipo() == TipoPedido.EXPRESS) {
            pedido.setCosto(pedido.getSubtotal().multiply(BigDecimal.valueOf(1.30)));
        } else if (pedido.getTipo() == TipoPedido.INTERNACIONAL) {
            pedido.setCosto(pedido.getSubtotal().multiply(BigDecimal.valueOf(1.50)).add(BigDecimal.valueOf(25.0)));
        }
        pedido.setEstado(EstadoPedido.PROCESADO);
        Pedido guardado = repo.guardar(pedido);
        mail.send(crearMensaje(guardado));
        return guardado;
    }

    private SimpleMailMessage crearMensaje(Pedido pedido) {
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo("cliente@example.com");
        mensaje.setSubject("Pedido procesado");
        mensaje.setText("Pedido " + pedido.getId() + " procesado por " + pedido.getCosto());
        return mensaje;
    }
}
