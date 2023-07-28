/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tapgroup.pwsalonreservas.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
import jakarta.validation.constraints.NotBlank;

import java.util.List;

import lombok.Data;

/**
 * @author chris
 */
@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "nombre_usuario")
    private String nombreUsuario;

    @Column(name = "contrasenia")
    private String contrasenia;

//    @NotBlank(message = "El estado no puede estar en blanco")
    @Column(name = "estado")
    private Boolean estado; // ACTIVO - INACTIVO

    //@Email(message = "Debe ingresar una dirección de correo válida")
    @Column(name = "email", length = 50, unique = true)
    private String email;

    // PERSONA A LA QUE PERTENECE EL USUARIO
   
    @JsonIgnoreProperties(value = {"dniPasaporte", "nombre", "apellido", "telefono", "celular", "fechaNac"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPersona", referencedColumnName = "id_persona")
    private Persona persona;

    //ROL DE USUARIO
    @JsonIgnoreProperties(value = {"nombre", "descripcion"})
    @ManyToOne()
    @JoinColumn(name = "id_rol", referencedColumnName = "id_rol")
    private Rol rol;
//    @JsonManagedReference(value = "rol")
//    @JsonIgnore
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_rol", referencedColumnName = "id_rol")
//    private Rol rol;

    // UN USUARIO PUEDE EMITIR MUCHAS CALIFICACIONES
    @JsonBackReference(value = "calificacionesByIdUsuario")
    @JsonIgnore
    @OneToMany(mappedBy = "usuarioByIdUsuario", fetch = FetchType.LAZY)
    private List<Calificacion> calificacionesByIdUsuario;

    // UNA USUARIO PUBLICA MUCHOS SALONES
    @JsonBackReference(value = "salonesByIdUsuario")
    @JsonIgnore
    @OneToMany(mappedBy = "usuarioPublicador", fetch = FetchType.LAZY)
    private List<Salon> salonesByIdUsuario;

    // UN USUARIO PUEDE REALIZAR MUCHOS PEDIDOS
    @JsonBackReference(value = "pedidosByIdUsuario")
    @JsonIgnore
    @OneToMany(mappedBy = "usuarioPedido", fetch = FetchType.LAZY)
    private List<Pedido> pedidosByIdUsuario;

}
