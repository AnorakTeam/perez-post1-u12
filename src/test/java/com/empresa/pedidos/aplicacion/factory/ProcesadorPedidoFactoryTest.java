package com.empresa.pedidos.aplicacion.factory;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.empresa.pedidos.adaptadores.procesadores.ProcesadorPedidoEstandar;
import com.empresa.pedidos.adaptadores.procesadores.ProcesadorPedidoExpress;
import com.empresa.pedidos.adaptadores.procesadores.ProcesadorPedidoInternacional;
import com.empresa.pedidos.dominio.TipoPedido;

class ProcesadorPedidoFactoryTest {

    @Test
    void factoryRetornaEstrategiaCorrectaPorTipo() {
        ProcesadorPedidoFactory factory = new ProcesadorPedidoFactory(List.of(
                new ProcesadorPedidoEstandar(),
                new ProcesadorPedidoExpress(),
                new ProcesadorPedidoInternacional()));

        assertInstanceOf(ProcesadorPedidoEstandar.class, factory.obtener(TipoPedido.ESTANDAR));
        assertInstanceOf(ProcesadorPedidoExpress.class, factory.obtener(TipoPedido.EXPRESS));
        assertInstanceOf(ProcesadorPedidoInternacional.class, factory.obtener(TipoPedido.INTERNACIONAL));
    }

    @Test
    void factoryRechazaTipoSinEstrategiaRegistrada() {
        ProcesadorPedidoFactory factory = new ProcesadorPedidoFactory(List.of(new ProcesadorPedidoEstandar()));

        assertThrows(IllegalArgumentException.class, () -> factory.obtener(TipoPedido.EXPRESS));
    }
}
