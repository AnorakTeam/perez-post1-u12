package com.empresa.pedidos.adaptadores.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.pedidos.adaptadores.facade.FachadaPedidos;
import com.empresa.pedidos.dominio.Pedido;

@RestController
@RequestMapping("/api/pedidos/legacy")
public class PedidoLegacyController {
    private final FachadaPedidos fachadaPedidos;

    public PedidoLegacyController(FachadaPedidos fachadaPedidos) {
        this.fachadaPedidos = fachadaPedidos;
    }

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> crear(@RequestBody PedidoRequestDTO request) {
        Pedido pedido = new Pedido(request.getTipo(), request.getSubtotal());
        Pedido creado = fachadaPedidos.crearPedido(pedido);
        return ResponseEntity.ok(new PedidoResponseDTO(
                creado.getId(),
                creado.getTipo(),
                creado.getSubtotal(),
                creado.getCosto(),
                creado.getEstado()));
    }
}
