package com.empresa.pedidos.adaptadores.procesadores;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.math.BigDecimal;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.empresa.pedidos.dominio.EstadoPedido;
import com.empresa.pedidos.dominio.Pedido;
import com.empresa.pedidos.dominio.TipoPedido;
import com.empresa.pedidos.dominio.puertos.ProcesadorPedido;

class ProcesadorPedidoTest {

    @ParameterizedTest
    @MethodSource("estrategias")
    void strategyCalculaCostoYEstado(ProcesadorPedido procesador, TipoPedido tipo, String costoEsperado) {
        Pedido pedido = new Pedido(tipo, new BigDecimal("100.00"));

        procesador.procesar(pedido);

        assertEquals(new BigDecimal(costoEsperado), pedido.getCosto());
        assertEquals(EstadoPedido.PROCESADO, pedido.getEstado());
    }

    private static Stream<Arguments> estrategias() {
        return Stream.of(
                arguments(new ProcesadorPedidoEstandar(), TipoPedido.ESTANDAR, "110.00"),
                arguments(new ProcesadorPedidoExpress(), TipoPedido.EXPRESS, "130.00"),
                arguments(new ProcesadorPedidoInternacional(), TipoPedido.INTERNACIONAL, "175.00"));
    }
}
