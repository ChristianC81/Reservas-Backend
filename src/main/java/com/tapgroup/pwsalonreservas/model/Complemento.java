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
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

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
@Table(name = "complemento")
public class Complemento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_complemento")
    private Integer idComplemento;

    @Column(name = "nombre")
    private String nombre;

    @NotBlank(message = "La detalle no puede estar en blanco")
    @Column(name = "descripcion", length = 60)
    private String descripcion;

    @Column(name = "cantidad_base")
    private Integer cantidadBase;

    @Column(name = "cantidad_restante")
    private Integer cantidadRestante;

    @Column(name = "precio_unitario")
    private Double precioUnitario;

    @Column(name = "estado")
    private Boolean estado;

    // SALON AL QUE PERTENECE EL COMPLEMENTO
    @JsonIgnore
    @JsonManagedReference(value = "salonByIdSalon")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_salon", referencedColumnName = "id_salon")
    private Salon salonByIdSalon;

    // DETALLES QUE USAN EL COMPLEMENTO
    @JsonBackReference(value = "detallesByIdComplemento")
    @JsonIgnore
    @OneToMany(mappedBy = "complementoByIdComplemento", fetch = FetchType.LAZY)
    private List<Detalle> detallesByIdComplemento;

}
