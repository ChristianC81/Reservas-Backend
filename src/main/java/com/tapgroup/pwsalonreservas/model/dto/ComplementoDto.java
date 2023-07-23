package com.tapgroup.pwsalonreservas.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComplementoDto {

    private Integer idComplemento;
    private String nombre;
    private String descripcion;
    private Integer cantidadBase;
    private Integer cantidadRestante;
    private Double precioUnitario;
    private Boolean estado;

}
