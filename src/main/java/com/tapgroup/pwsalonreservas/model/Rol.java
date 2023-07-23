/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tapgroup.pwsalonreservas.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author chris
 */
@Getter
@Setter
@Entity
@Table(name = "rol")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Integer idRol;

    @Size(min = 3, max = 20, message = "El usuario debe tener entre 3 y 10 caracteres")
    @NotBlank(message = "El nombre de rol no puede estar en blanco")
    @Column(name = "nombre")
    private String nombre;

    @NotBlank(message = "La detalle no puede estar en blanco")
    @Column(name = "descripcion")
    private String descripcion;


    // RELACION CON USUARIO
    @JsonBackReference(value="usersByRol")
    @JsonIgnore
    @OneToMany(mappedBy="rol", fetch = FetchType.LAZY)
    private List<Usuario> usersByRol;

}
