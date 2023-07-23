package com.tapgroup.pwsalonreservas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

@Getter
@Setter
@Entity
@Table(name = "multimedia")
public class Multimedia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_multimedia")
    private Integer idMultimedia;

    @Column(name = "url", length = 200)
    private String url;

    @Column(name = "imagen", columnDefinition = "TEXT")
    @Basic(fetch = FetchType.LAZY)
    private String imagen;

    // TIPO DE MULTIMEDIA
    @JsonManagedReference(value = "tipoMultimedia")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_multimedia", referencedColumnName = "id_tipo_multimedia")
    private TipoMultimedia tipoMultimedia;

    // SALON AL QUE PERTENECE LA MULTIMEDIA
    @JsonManagedReference(value = "salon")
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_salon", referencedColumnName = "id_salon")
    private Salon salon;
}
