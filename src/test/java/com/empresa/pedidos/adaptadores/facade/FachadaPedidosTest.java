package com.empresa.pedidos.adaptadores.facade;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.empresa.pedidos.aplicacion.ServicioPedidos;
import com.empresa.pedidos.dominio.Pedido;
import com.empresa.pedidos.dominio.PedidoId;
import com.empresa.pedidos.dominio.TipoPedido;

@ExtendWith(MockitoExtension.class)
class FachadaPedidosTest {

    @Mock
    private ServicioPedidos servicioPedidos;
    @InjectMocks
    private FachadaPedidos fachadaPedidos;

    @Test
    void facadeExponeCreacionSinRevelarServicioInterno() {
        Pedido pedido = new Pedido(TipoPedido.ESTANDAR, new BigDecimal("10.00"));
        when(servicioPedidos.crearPedido(pedido)).thenReturn(pedido);

        assertEquals(pedido, fachadaPedidos.crearPedido(pedido));
        verify(servicioPedidos).crearPedido(pedido);
    }

    @Test
    void facadeConvierteIdentificadorEnBusqueda() {
        Pedido pedido = new Pedido(TipoPedido.ESTANDAR, new BigDecimal("10.00"));
        when(servicioPedidos.buscarPorId(new PedidoId(1L))).thenReturn(Optional.of(pedido));

        assertEquals(Optional.of(pedido), fachadaPedidos.buscarPorId(1L));
    }
}
