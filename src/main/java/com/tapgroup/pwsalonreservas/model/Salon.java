/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tapgroup.pwsalonreservas.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.sql.Date;
import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author chris
 */
@Getter
@Setter
@Entity
@Table(name = "salon")
public class Salon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_salon")
    private Integer idSalon;

    @Size(min = 3, max = 30, message = "El nombre del salon debe tener entre 3 y 30 caracteres")
    @NotBlank(message = "El salon no puede estar en blanco")
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "direccion", length = 100)
    private String direccion;

    @Column(name = "capacidad")
    private Integer capacidad;

    @Column(name = "disponibilidad")
    private Boolean disponibilidad;

    @Column(name = "descripcion", length = 100)
    private String descripcion;

    @Column(name = "precio_salon")
    private Double precioSalon;

    @Column(name = "calificacion")
    private Double calificacion;

    @Column(name = "estado")
    private Boolean estado;

    @Column(name = "garantiaDanos")
    private Double garantiaDanos;

    // USUARIO QUE PUBLICA EL SALON
    @JsonIgnore
    @JsonManagedReference(value = "usuarioPublicador")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuariopublicador", referencedColumnName = "id_usuario")
    private Usuario usuarioPublicador;

    // CALIFICACIONES DEL SALON
    @JsonIgnore
    @JsonBackReference(value = "calificacionesBySalon")
    @OneToMany(mappedBy = "salonByIdSalon", fetch = FetchType.LAZY)
    private List<Calificacion> calificacionesBySalon;

    // COMPLEMENTOS DEL SALON
    @JsonBackReference(value = "complementosBySalon")
    @OneToMany(mappedBy = "salonByIdSalon", fetch = FetchType.LAZY)
    private List<Complemento> complementosBySalon;

    // RESERVAS DEL SALON
    @JsonIgnore
    @JsonBackReference(value = "reservasBySalon")
    @OneToMany(mappedBy = "salon", fetch = FetchType.LAZY)
    private List<Pedido> reservasBySalon;

    // CATEGORIA DEL SALON
    @JsonManagedReference(value = "categoria")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria", referencedColumnName = "id_categoria")
    private Categoria categoria;

    // MULTIMEDIA DEL SALON
    @JsonBackReference(value = "multimediaBySalon")
    @JsonIgnore
    @OneToMany(mappedBy = "salon", fetch = FetchType.LAZY)
    private List<Multimedia> multimediaBySalon;
}
