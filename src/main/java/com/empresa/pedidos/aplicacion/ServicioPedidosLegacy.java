package com.empresa.pedidos.aplicacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.empresa.pedidos.dominio.EstadoPedido;
import com.empresa.pedidos.dominio.Pedido;
import com.empresa.pedidos.dominio.TipoPedido;
import com.empresa.pedidos.infraestructura.persistencia.PedidoRepository;

/**
 * Baseline intencional: concentra calculo, persistencia y notificacion.
 * Compila, pero conserva los olores de diseno que deben observarse en Sonar.
 */
@Service
public class ServicioPedidosLegacy {

    @Autowired
    private PedidoRepository repo;

    @Autowired
    private JavaMailSender mail;

    public Pedido procesarPedido(Pedido pedido) {
        if (pedido.getTipo() == TipoPedido.ESTANDAR) {
            pedido.setCosto(pedido.getSubtotal() * 1.1);
        } else if (pedido.getTipo() == TipoPedido.EXPRESS) {
            pedido.setCosto(pedido.getSubtotal() * 1.3);
        } else if (pedido.getTipo() == TipoPedido.INTERNACIONAL) {
            pedido.setCosto(pedido.getSubtotal() * 1.5 + 25.0);
        }
        pedido.setEstado(EstadoPedido.PROCESADO);
        Pedido guardado = repo.save(pedido);
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
