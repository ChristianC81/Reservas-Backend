package com.tapgroup.pwsalonreservas.model.dto;

import com.tapgroup.pwsalonreservas.model.Complemento;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SalonDto {

    private Integer idSalon;
    private String nombre;
    private String direccion;
    private Integer capacidad;
    private Boolean disponibilidad;
    private String descripcion;
    private Double precioSalon;
    private Double calificacion;
    private Boolean estado;
    private Double garantiaDanos;
    private String emailUsuario;
    private CategoriaDto categoria;
    private List<ComplementoDto> complementos;
}
