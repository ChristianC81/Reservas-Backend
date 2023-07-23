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
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Integer idCategoria;

    @Column(name = "categoria", length = 20)
    private String categoria;

    @Column(name = "estado")
    private Boolean estado;

    // SALONES POR CATEGORIA
    @JsonBackReference(value = "salonCategoriaByIdCategoria")
    @JsonIgnore
    @OneToMany(mappedBy = "categoria", fetch = FetchType.LAZY)
    private List<Salon> salonCategoriaByIdCategoria;
}
