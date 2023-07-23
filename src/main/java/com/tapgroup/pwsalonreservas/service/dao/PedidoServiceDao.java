package com.tapgroup.pwsalonreservas.service.dao;

import com.tapgroup.pwsalonreservas.model.dto.PedidoDto;
import org.springframework.http.ResponseEntity;


public interface PedidoServiceDao {

    ResponseEntity<?> postPedido(PedidoDto pedidoDto);

    ResponseEntity<?> cancelarPedidoByUsuarioPedido(Integer idPedido);

    ResponseEntity<?> aceptarOrechazarPedidoBySalon(Integer idPedido, Boolean aceptado);

    ResponseEntity<?> getPedidosBySalon(Integer idSalon, String emailUsuario);

}
