package com.empresa.pedidos.adaptadores.rest;

import java.math.BigDecimal;

import com.empresa.pedidos.dominio.TipoPedido;

public class PedidoRequestDTO {

    private TipoPedido tipo;
    private BigDecimal subtotal;

    public PedidoRequestDTO() {
    }

    public PedidoRequestDTO(TipoPedido tipo, BigDecimal subtotal) {
        this.tipo = tipo;
        this.subtotal = subtotal;
    }

    public TipoPedido getTipo() {
        return tipo;
    }

    public void setTipo(TipoPedido tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
}
