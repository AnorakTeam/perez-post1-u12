package com.empresa.pedidos.aplicacion;

import java.util.Optional;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.empresa.pedidos.aplicacion.factory.ProcesadorPedidoFactory;
import com.empresa.pedidos.dominio.Pedido;
import com.empresa.pedidos.dominio.PedidoId;
import com.empresa.pedidos.dominio.eventos.PedidoProcesadoEvent;
import com.empresa.pedidos.dominio.puertos.RepositorioPedidos;

@Service
public class ServicioPedidos {

    private final ProcesadorPedidoFactory procesadorPedidoFactory;
    private final RepositorioPedidos repositorioPedidos;
    private final ApplicationEventPublisher eventPublisher;

    public ServicioPedidos(ProcesadorPedidoFactory procesadorPedidoFactory,
                           RepositorioPedidos repositorioPedidos,
                           ApplicationEventPublisher eventPublisher) {
        this.procesadorPedidoFactory = procesadorPedidoFactory;
        this.repositorioPedidos = repositorioPedidos;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public Pedido crearPedido(Pedido pedido) {
        procesadorPedidoFactory.obtener(pedido.getTipo()).procesar(pedido);
        Pedido guardado = repositorioPedidos.guardar(pedido);
        eventPublisher.publishEvent(new PedidoProcesadoEvent(guardado));
        return guardado;
    }

    @Transactional(readOnly = true)
    public Optional<Pedido> buscarPorId(PedidoId id) {
        return repositorioPedidos.buscarPorId(id);
    }
}
