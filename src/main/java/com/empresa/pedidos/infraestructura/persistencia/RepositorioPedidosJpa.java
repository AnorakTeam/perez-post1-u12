package com.empresa.pedidos.infraestructura.persistencia;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.empresa.pedidos.dominio.Pedido;
import com.empresa.pedidos.dominio.PedidoId;
import com.empresa.pedidos.dominio.puertos.RepositorioPedidos;

@Repository
public class RepositorioPedidosJpa implements RepositorioPedidos {

    private final PedidoSpringDataRepository repository;

    public RepositorioPedidosJpa(PedidoSpringDataRepository repository) {
        this.repository = repository;
    }

    @Override
    public Pedido guardar(Pedido pedido) {
        return repository.save(pedido);
    }

    @Override
    public Optional<Pedido> buscarPorId(PedidoId id) {
        return repository.findById(id.valor());
    }
}
