/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tapgroup.pwsalonreservas.model;

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
 * @author jonny
 */
@Getter
@Setter
@Entity
@Table(name = "detalle")
public class Detalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle")
    private Integer idDetalle;

    @Column(name = "cantidad")
    private Double cantidad;

    @Column(name = "precio_unitario")
    private Double precioUnitario;

    // PEDIDO AL QUE PERTENECE EL DETALLE
    @JsonManagedReference(value = "pedido")
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pedido", referencedColumnName = "id_pedido")
    private Pedido pedido;

    // COMPLEMENTO AL QUE PERTENECE EL DETALLE
    @JsonManagedReference(value = "complementoByIdComplemento")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_complemento", referencedColumnName = "id_complemento")
    private Complemento complementoByIdComplemento;
}
