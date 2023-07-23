package com.tapgroup.pwsalonreservas.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PedidoDto {
    private Integer pedId;
    private Integer pedCantidad;
    private Double pedPreciocomplementos;
    private Double pedPreciototal;
    private String pedObservacion;
    private Boolean pedEstadopago;
    private String pedFechaInicio;
    private String pedFechaFin;
    private String pedFechaEnvioSolicitud;
    private String estado;
    //RELACIONES
    private String pedEmailUsuario;
    private Integer pedSalon;
    private List<DetallePedidoDto> pedDetalle;

}
