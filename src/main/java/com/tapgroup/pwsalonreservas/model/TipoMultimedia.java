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
@Table(name = "tipo_multimedia")
public class TipoMultimedia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_multimedia")
    private Integer idTipoMultimedia;

    @Column(name = "tipo_multimedia", length = 20)
    private String tipoMultimedia;

    // MULTIMEDIA POR TIPO
    @JsonIgnore
    @JsonBackReference(value = "multimediaByTipoMultimedia")
    @OneToMany(mappedBy = "tipoMultimedia", fetch = FetchType.LAZY)
    private List<Multimedia> multimediaByTipoMultimedia;
}
