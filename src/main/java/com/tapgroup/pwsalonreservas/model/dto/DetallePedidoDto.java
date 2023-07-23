package com.tapgroup.pwsalonreservas.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetallePedidoDto {

    private Integer idDetalle;
    private Integer idComplemento;
    private String nombreComplemento;
    private Double cantidad;
    private Double precioUnitario;

}
