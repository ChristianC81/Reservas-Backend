/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
 * @author chris
 */
@Getter
@Setter
@Entity
@Table(name = "calificacion")
public class Calificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_calificacion")
    private Integer idCalificacion;

    @Column(name = "valor")
    private Integer valor;

    //Funcion que estime las calificaciones

    // USUARIO QUE REALIZO LA CALIFICACION
    @JsonManagedReference(value = "usuarioByIdUsuario")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private Usuario usuarioByIdUsuario;

    // SALON QUE RECIBIO LA CALIFICACION
    @JsonManagedReference(value = "salonByIdSalon")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_salon", referencedColumnName = "id_salon")
    private Salon salonByIdSalon;
}
