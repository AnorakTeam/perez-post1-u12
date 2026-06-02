package com.empresa.pedidos.adaptadores.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.pedidos.adaptadores.facade.FachadaPedidos;
import com.empresa.pedidos.dominio.Pedido;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final FachadaPedidos fachadaPedidos;

    public PedidoController(FachadaPedidos fachadaPedidos) {
        this.fachadaPedidos = fachadaPedidos;
    }

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> crear(@RequestBody PedidoRequestDTO request) {
        Pedido pedido = new Pedido(request.getTipo(), request.getSubtotal());
        Pedido creado = fachadaPedidos.crearPedido(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(creado));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.of(fachadaPedidos.buscarPorId(id).map(PedidoController::toResponse));
    }

    private static PedidoResponseDTO toResponse(Pedido pedido) {
        return new PedidoResponseDTO(
                pedido.getId(),
                pedido.getTipo(),
                pedido.getSubtotal(),
                pedido.getCosto(),
                pedido.getEstado());
    }
}
