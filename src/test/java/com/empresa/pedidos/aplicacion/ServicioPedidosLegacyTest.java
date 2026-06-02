package com.empresa.pedidos.aplicacion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.empresa.pedidos.dominio.EstadoPedido;
import com.empresa.pedidos.dominio.Pedido;
import com.empresa.pedidos.dominio.TipoPedido;
import com.empresa.pedidos.infraestructura.persistencia.PedidoRepository;

@ExtendWith(MockitoExtension.class)
class ServicioPedidosLegacyTest {
    @Mock private PedidoRepository repo;
    @Mock private JavaMailSender mail;
    @InjectMocks private ServicioPedidosLegacy servicio;

    @Test
    void procesaExpressConDependenciasAcopladas() {
        Pedido pedido = new Pedido(TipoPedido.EXPRESS, 100.0);
        when(repo.save(pedido)).thenReturn(pedido);

        Pedido resultado = servicio.procesarPedido(pedido);

        assertEquals(130.0, resultado.getCosto());
        assertEquals(EstadoPedido.PROCESADO, resultado.getEstado());
        verify(repo).save(pedido);
        verify(mail).send(org.mockito.ArgumentMatchers.any(SimpleMailMessage.class));
    }
}
