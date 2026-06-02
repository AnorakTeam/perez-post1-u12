package com.empresa.pedidos.aplicacion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import com.empresa.pedidos.aplicacion.factory.ProcesadorPedidoFactory;
import com.empresa.pedidos.dominio.Pedido;
import com.empresa.pedidos.dominio.PedidoId;
import com.empresa.pedidos.dominio.TipoPedido;
import com.empresa.pedidos.dominio.eventos.PedidoProcesadoEvent;
import com.empresa.pedidos.dominio.puertos.ProcesadorPedido;
import com.empresa.pedidos.dominio.puertos.RepositorioPedidos;

@ExtendWith(MockitoExtension.class)
class ServicioPedidosTest {

    @Mock
    private ProcesadorPedidoFactory factory;
    @Mock
    private RepositorioPedidos repositorio;
    @Mock
    private ApplicationEventPublisher publisher;
    @Mock
    private ProcesadorPedido procesador;
    @InjectMocks
    private ServicioPedidos servicio;

    @Test
    void observerSePublicaAlCrearPedido() {
        Pedido pedido = new Pedido(TipoPedido.EXPRESS, new BigDecimal("100.00"));
        when(factory.obtener(TipoPedido.EXPRESS)).thenReturn(procesador);
        when(repositorio.guardar(pedido)).thenReturn(pedido);

        assertEquals(pedido, servicio.crearPedido(pedido));

        verify(procesador).procesar(pedido);
        verify(repositorio).guardar(pedido);
        verify(publisher).publishEvent(any(PedidoProcesadoEvent.class));
    }

    @Test
    void buscaPedidoPorPuertoDePersistencia() {
        Pedido pedido = new Pedido(TipoPedido.ESTANDAR, new BigDecimal("40.00"));
        PedidoId id = new PedidoId(1L);
        when(repositorio.buscarPorId(id)).thenReturn(Optional.of(pedido));

        assertEquals(Optional.of(pedido), servicio.buscarPorId(id));
    }
}
