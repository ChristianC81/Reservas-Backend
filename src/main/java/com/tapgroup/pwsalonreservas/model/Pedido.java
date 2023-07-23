/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tapgroup.pwsalonreservas.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

import java.sql.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * @author chris
 */
@Getter
@Setter
@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Integer idPedido;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "preciototal")
    private Double precioTotal;

    @Column(name = "observacion")
    private String observacion;

    @Column(name = "estadodepago")
    private Boolean estadoDePago;

    @Column(name = "fechainicio")
    private Date fechaInicio;

    @Column(name = "fechafin")
    private Date fechaFin;

    @Column(name = "fechapedido")
    private Date fechaPedido;

    @Column(name = "estado")
    private String estado;

    // USUARIO QUE REALIZA EL PEDIDO
    @JsonManagedReference(value = "usuarioPedido")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario_reservador", referencedColumnName = "id_usuario")
    private Usuario usuarioPedido;

    // SALON QUE RECIBIO EL PEDIDO
    @JsonManagedReference(value = "salon")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_salon", referencedColumnName = "id_salon")
    private Salon salon;

    // DETALLES DEL PEDIDO
    @JsonBackReference(value = "detallesByPedido")
    @OneToMany(mappedBy = "pedido", fetch = FetchType.LAZY)
    private List<Detalle> detallesByPedido;

}
