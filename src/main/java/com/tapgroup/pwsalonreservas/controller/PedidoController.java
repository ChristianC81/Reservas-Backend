/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tapgroup.pwsalonreservas.controller;

import com.tapgroup.pwsalonreservas.model.Pedido;
import com.tapgroup.pwsalonreservas.model.dto.DetallePedidoDto;
import com.tapgroup.pwsalonreservas.model.dto.PedidoDto;
import com.tapgroup.pwsalonreservas.service.PedidoServiceImpl;
import com.tapgroup.pwsalonreservas.service.dao.PedidoServiceDao;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jonny
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/pedido")
public class PedidoController {

    @Autowired
    PedidoServiceDao pedidoServiceDao;

    //    @Operation(summary = "Debe enviar los campos de Pedido")
//    @PostMapping("/crear")
//    public ResponseEntity<?> crearPedido(@RequestParam Integer idSalon, @RequestParam String emailUsuario, @RequestParam String fechaInicio, @RequestParam String fechaFin, @RequestParam String observacion, @RequestParam Double precioTotal, @RequestParam Integer cantidad, @RequestBody List<DetallePedidoDto> detallePedido) {
//        return pedidoServiceDao.postPedido(idSalon, emailUsuario, fechaInicio, fechaFin, observacion, precioTotal, cantidad, detallePedido);
//    }
    @Operation(summary = "Debe enviar los campos de Pedido")
    @PostMapping("/crear")
    public ResponseEntity<?> crearPedido(@RequestBody PedidoDto pedido) {
        return pedidoServiceDao.postPedido(pedido);
    }

    @PostMapping("/cancelarPedidoByUsuarioPedido/{idPedido}")
    public ResponseEntity<?> cancelarPedidoByUsuarioPedido(@PathVariable Integer idPedido) {
        return pedidoServiceDao.cancelarPedidoByUsuarioPedido(idPedido);
    }

    @PostMapping("/aceptarOrechazarPedidoBySalon/{idPedido}")
    public ResponseEntity<?> aceptarOrechazarPedidoBySalon(@PathVariable Integer idPedido, @RequestParam Boolean aceptado) {
        return pedidoServiceDao.aceptarOrechazarPedidoBySalon(idPedido, aceptado);
    }

    @GetMapping("/getPedidosBySalon/{idSalon}")
    public ResponseEntity<?> getPedidosBySalon(@PathVariable Integer idSalon, @RequestParam String emailUsuario) {
        return pedidoServiceDao.getPedidosBySalon(idSalon, emailUsuario);
    }

}
