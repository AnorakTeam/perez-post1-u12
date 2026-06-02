package com.empresa.pedidos.aplicacion.factory;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.empresa.pedidos.dominio.TipoPedido;
import com.empresa.pedidos.dominio.puertos.ProcesadorPedido;

@Component
public class ProcesadorPedidoFactory {

    private final Map<TipoPedido, ProcesadorPedido> procesadores;

    public ProcesadorPedidoFactory(List<ProcesadorPedido> procesadores) {
        this.procesadores = procesadores.stream()
                .collect(Collectors.toUnmodifiableMap(ProcesadorPedido::getTipo, Function.identity()));
    }

    public ProcesadorPedido obtener(TipoPedido tipo) {
        ProcesadorPedido procesador = procesadores.get(tipo);
        if (procesador == null) {
            throw new IllegalArgumentException("Tipo de pedido no soportado: " + tipo);
        }
        return procesador;
    }
}
