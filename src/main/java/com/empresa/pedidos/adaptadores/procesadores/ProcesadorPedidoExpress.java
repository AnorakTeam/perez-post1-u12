package com.empresa.pedidos.adaptadores.procesadores;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.empresa.pedidos.dominio.EstadoPedido;
import com.empresa.pedidos.dominio.Pedido;
import com.empresa.pedidos.dominio.TipoPedido;
import com.empresa.pedidos.dominio.puertos.ProcesadorPedido;

@Component
public class ProcesadorPedidoExpress implements ProcesadorPedido {

    private static final BigDecimal FACTOR = new BigDecimal("1.30");

    @Override
    public TipoPedido getTipo() {
        return TipoPedido.EXPRESS;
    }

    @Override
    public void procesar(Pedido pedido) {
        pedido.setCosto(pedido.getSubtotal().multiply(FACTOR));
        pedido.setEstado(EstadoPedido.PROCESADO);
    }
}
