package com.empresa.pedidos.adaptadores.rest;

import java.math.BigDecimal;

import com.empresa.pedidos.dominio.EstadoPedido;
import com.empresa.pedidos.dominio.TipoPedido;

public class PedidoResponseDTO {

    private Long id;
    private TipoPedido tipo;
    private BigDecimal subtotal;
    private BigDecimal costo;
    private EstadoPedido estado;

    public PedidoResponseDTO() {
    }

    public PedidoResponseDTO(Long id, TipoPedido tipo, BigDecimal subtotal, BigDecimal costo, EstadoPedido estado) {
        this.id = id;
        this.tipo = tipo;
        this.subtotal = subtotal;
        this.costo = costo;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }
}
