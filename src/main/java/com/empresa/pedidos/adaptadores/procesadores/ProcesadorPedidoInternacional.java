package com.empresa.pedidos.adaptadores.procesadores;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.empresa.pedidos.dominio.EstadoPedido;
import com.empresa.pedidos.dominio.Pedido;
import com.empresa.pedidos.dominio.TipoPedido;
import com.empresa.pedidos.dominio.puertos.ProcesadorPedido;

@Component
public class ProcesadorPedidoInternacional implements ProcesadorPedido {

    private static final BigDecimal FACTOR = new BigDecimal("1.50");
    private static final BigDecimal TARIFA_INTERNACIONAL = new BigDecimal("25.00");

    @Override
    public TipoPedido getTipo() {
        return TipoPedido.INTERNACIONAL;
    }

    @Override
    public void procesar(Pedido pedido) {
        pedido.setCosto(pedido.getSubtotal().multiply(FACTOR).add(TARIFA_INTERNACIONAL));
        pedido.setEstado(EstadoPedido.PROCESADO);
    }
}
