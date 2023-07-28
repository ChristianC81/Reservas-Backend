/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tapgroup.pwsalonreservas.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.sql.Date;
import java.util.List;

import jakarta.persistence.Table;
import lombok.*;

/**
 *
 * @author chris
 */
@Getter
@Setter
@Entity
@Table(name = "persona")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona")
    private Integer idPersona;

    @Column(name = "dni_pasaporte", length = 10, unique = true)
    private String dniPasaporte;

    //@Size(min = 3, max = 10, message = "La persona debe tener entre 3 y 10 caracteres")
    //@NotBlank(message = "La persona no puede estar en blanco")
    @Column(name = "nombre", length = 50)
    private String nombre;

    //@NotBlank(message = "La clave no puede estar en blanco")
    @Column(name = "apellido", length = 50)
    private String apellido;

    @Column(name = "telefono", length = 15)
    private String telefono;

    @Column(name = "celular", length = 14)
    private String celular;

    @Column(name = "fecha_nac")
    private Date fechaNac;

    // USUARIOS DE LA PERSONA
    @JsonBackReference(value = "persona")
    @OneToMany(mappedBy = "persona", fetch = FetchType.LAZY)
    private List<Usuario> usersByPersona;

}
