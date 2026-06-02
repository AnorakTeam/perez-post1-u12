package com.empresa.pedidos.dominio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class PedidoTest {

    @Test
    void normalizaValoresMonetariosADosDecimales() {
        Pedido pedido = new Pedido(TipoPedido.ESTANDAR, new BigDecimal("10.126"));
        pedido.setCosto(new BigDecimal("11.139"));

        assertEquals(new BigDecimal("10.13"), pedido.getSubtotal());
        assertEquals(new BigDecimal("11.14"), pedido.getCosto());
    }

    @Test
    void rechazaSubtotalNegativoEIdentificadorInvalido() {
        assertThrows(IllegalArgumentException.class,
                () -> new Pedido(TipoPedido.ESTANDAR, new BigDecimal("-1.00")));
        assertThrows(IllegalArgumentException.class, () -> new PedidoId(0L));
    }
}
