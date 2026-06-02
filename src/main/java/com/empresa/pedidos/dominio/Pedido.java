package com.empresa.pedidos.dominio;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoPedido tipo;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal subtotal;

    @Column(precision = 12, scale = 2)
    private BigDecimal costo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoPedido estado = EstadoPedido.CREADO;

    public Pedido() {
        // Requerido por JPA y Jackson.
    }

    public Pedido(TipoPedido tipo, BigDecimal subtotal) {
        setTipo(tipo);
        setSubtotal(subtotal);
    }

    public Pedido(TipoPedido tipo, double subtotal) {
        this(tipo, BigDecimal.valueOf(subtotal));
    }

    public Long getId() {
        return id;
    }

    public TipoPedido getTipo() {
        return tipo;
    }

    public void setTipo(TipoPedido tipo) {
        this.tipo = Objects.requireNonNull(tipo, "El tipo es obligatorio");
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        Objects.requireNonNull(subtotal, "El subtotal es obligatorio");
        if (subtotal.signum() < 0) {
            throw new IllegalArgumentException("El subtotal no puede ser negativo");
        }
        this.subtotal = subtotal.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = Objects.requireNonNull(costo, "El costo es obligatorio")
                .setScale(2, RoundingMode.HALF_UP);
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = Objects.requireNonNull(estado, "El estado es obligatorio");
    }
}
