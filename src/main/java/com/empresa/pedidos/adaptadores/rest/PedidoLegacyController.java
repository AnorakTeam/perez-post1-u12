package com.empresa.pedidos.adaptadores.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.pedidos.aplicacion.ServicioPedidosLegacy;
import com.empresa.pedidos.dominio.Pedido;

@RestController
@RequestMapping("/api/pedidos/legacy")
public class PedidoLegacyController {
    private final ServicioPedidosLegacy servicio;
    public PedidoLegacyController(ServicioPedidosLegacy servicio) { this.servicio = servicio; }
    @PostMapping
    public ResponseEntity<Pedido> crear(@RequestBody Pedido pedido) {
        return ResponseEntity.ok(servicio.procesarPedido(pedido));
    }
}
