/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tapgroup.pwsalonreservas.service;

import com.tapgroup.pwsalonreservas.model.Detalle;
import com.tapgroup.pwsalonreservas.model.Pedido;
import com.tapgroup.pwsalonreservas.model.Salon;
import com.tapgroup.pwsalonreservas.model.Usuario;
import com.tapgroup.pwsalonreservas.model.dto.DetallePedidoDto;
import com.tapgroup.pwsalonreservas.model.dto.PedidoDto;
import com.tapgroup.pwsalonreservas.repository.ComplementoRepository;
import com.tapgroup.pwsalonreservas.repository.DetalleRepository;
import com.tapgroup.pwsalonreservas.repository.PedidoRepository;

import com.tapgroup.pwsalonreservas.repository.SalonRepository;
import com.tapgroup.pwsalonreservas.repository.UsuarioRepository;
import com.tapgroup.pwsalonreservas.service.dao.PedidoServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chris
 */
@Service
public class PedidoServiceImpl implements PedidoServiceDao {

    private final String estadoPedidoPendiente = "PENDIENTE";
    private final String estadoPedidoAceptado = "ACEPTADO";
    private final String estadoPedidoRechazado = "RECHAZADO";
    private final String estadoPedidoCancelado = "CANCELADO";

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    SalonRepository salonRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ComplementoRepository complementoRepository;

    @Autowired
    DetalleRepository detalleRepository;

    @Override
    public ResponseEntity<?> postPedido(PedidoDto pedidoDto) {
        Salon salon = salonRepository.findById(pedidoDto.getPedSalon()).orElse(null);

        if (null == salon) {
            return ResponseEntity.badRequest().body("El salon no existe");
        }

        Usuario usuario = usuarioRepository.findByEmail(pedidoDto.getPedEmailUsuario());

        if (null == usuario) {
            return ResponseEntity.badRequest().body("El usuario no existe");
        }

        Pedido pedido = new Pedido();
        pedido.setEstado(estadoPedidoPendiente);
        pedido.setEstadoDePago(false);
        pedido.setFechaPedido(new Date(System.currentTimeMillis()));
        pedido.setFechaInicio(Date.valueOf(pedidoDto.getPedFechaInicio()));
        pedido.setFechaFin(Date.valueOf(pedidoDto.getPedFechaFin()));
        pedido.setObservacion(pedidoDto.getPedObservacion());
        pedido.setPrecioTotal(pedido.getPrecioTotal());
        pedido.setCantidad(pedidoDto.getPedCantidad());
        pedido.setSalon(salon);
        pedido.setUsuarioPedido(usuario);

        pedido = pedidoRepository.save(pedido);

        Pedido finalPedido = pedido;
        Double precioTotal = 0.0;
        for (DetallePedidoDto detalle : pedidoDto.getPedDetalle()) {
            Detalle det = new Detalle();
            det.setCantidad(detalle.getCantidad());
            det.setPrecioUnitario(detalle.getPrecioUnitario());
            det.setComplementoByIdComplemento(complementoRepository.findById(detalle.getIdComplemento()).orElse(null));
            det.setPedido(finalPedido);
            precioTotal += (det.getPrecioUnitario() * det.getCantidad());
            detalleRepository.save(det);
        }

        precioTotal = precioTotal + pedido.getSalon().getPrecioSalon();
        precioTotal = precioTotal * pedido.getCantidad();
        pedido.setPrecioTotal(precioTotal);
        pedidoRepository.save(pedido);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> cancelarPedidoByUsuarioPedido(Integer idPedido) {
        Pedido pedido = pedidoRepository.findById(idPedido).orElse(null);

        if (null == pedido) {
            return ResponseEntity.badRequest().body("El pedido no existe");
        }

        if (pedido.getEstado().equals(estadoPedidoPendiente)) {
            pedido.setEstado(estadoPedidoCancelado);
            pedidoRepository.save(pedido);
            return new ResponseEntity<>("Pedido cancelado exitosamente", HttpStatus.OK);
        }

        return new ResponseEntity<>("El pedido ya fue aceptado o rechazado previamente, no se puede cancelar.", HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<?> aceptarOrechazarPedidoBySalon(Integer idPedido, Boolean aceptado) {

        Pedido pedido = pedidoRepository.findById(idPedido).orElse(null);

        if (null == pedido) {
            return ResponseEntity.badRequest().body("El pedido no existe");
        }

        if (pedido.getEstado().equals(estadoPedidoPendiente)) {
            if (aceptado) {
                pedido.setEstado(estadoPedidoAceptado);
            } else {
                pedido.setEstado(estadoPedidoRechazado);
            }
            String mensaje = aceptado ? "aceptado" : "rechazado";
            pedidoRepository.save(pedido);
            return new ResponseEntity<>("Pedido " + mensaje + " exitosamente.", HttpStatus.OK);
        }

        return null;
    }

    @Override
    public ResponseEntity<?> getPedidosBySalon(Integer idSalon, String emailUsuario) {
        Salon salon = salonRepository.findById(idSalon).orElse(null);

        if (null == salon) {
            return ResponseEntity.badRequest().body("El salon no existe");
        }

        Usuario usuario = usuarioRepository.findByEmail(emailUsuario);

        if (null == usuario) {
            return ResponseEntity.badRequest().body("El usuario no existe");
        }

        List<Pedido> pedidos = pedidoRepository.findBySalonAndSalon_UsuarioPublicador(salon, usuario);

        if (null == pedidos) {
            return ResponseEntity.badRequest().body("No hay pedidos para este salon");
        }

        List<PedidoDto> pedidosDto = new ArrayList<>();

        pedidos.forEach((pedido -> {
            PedidoDto pedidoDto = new PedidoDto();
            pedidoDto.setPedId(pedido.getIdPedido());
            pedidoDto.setPedCantidad(pedido.getCantidad());
            pedidoDto.setPedPreciocomplementos(pedido.getPrecioTotal());
            pedidoDto.setPedPreciototal(pedido.getPrecioTotal());
            pedidoDto.setPedObservacion(pedido.getObservacion());
            pedidoDto.setPedEstadopago(pedido.getEstadoDePago());
            pedidoDto.setPedFechaInicio(pedido.getFechaInicio().toString());
            pedidoDto.setPedFechaFin(pedido.getFechaFin().toString());
            pedidoDto.setPedEmailUsuario(pedido.getUsuarioPedido().getEmail());
            pedidoDto.setPedSalon(pedido.getSalon().getIdSalon());
            pedidoDto.setPedFechaEnvioSolicitud(pedido.getFechaPedido().toString());
            pedidoDto.setEstado(pedido.getEstado());

            List<DetallePedidoDto> detallesDto = new ArrayList<>();

            pedido.getDetallesByPedido().forEach((detalle) -> {
                DetallePedidoDto detalleDto = new DetallePedidoDto();
                detalleDto.setIdDetalle(detalle.getIdDetalle());
                detalleDto.setCantidad(detalle.getCantidad());
                detalleDto.setPrecioUnitario(detalle.getPrecioUnitario());
                detalleDto.setIdComplemento(detalle.getComplementoByIdComplemento().getIdComplemento());
                detalleDto.setNombreComplemento(detalle.getComplementoByIdComplemento().getNombre());
                detallesDto.add(detalleDto);
            });

            pedidoDto.setPedDetalle(detallesDto);

            pedidosDto.add(pedidoDto);
        }));

        return new ResponseEntity<>(pedidosDto, HttpStatus.OK);
    }
}

