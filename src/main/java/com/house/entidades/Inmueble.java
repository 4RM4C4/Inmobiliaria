package com.house.entidades;

import com.house.Enumeraciones.TiposInmueble;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Inmueble {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private Long id;
    private String direccion;
    @ManyToOne
    private Ente dueño;
    @Enumerated(EnumType.STRING)
    private TiposInmueble tiposInmueble;

    public Inmueble() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Ente getDueño() {
        return dueño;
    }

    public void setDueño(Ente dueño) {
        this.dueño = dueño;
    }

    public TiposInmueble getTiposInmueble() {
        return tiposInmueble;
    }

    public void setTiposInmueble(TiposInmueble tiposInmueble) {
        this.tiposInmueble = tiposInmueble;
    }

    public Inmueble(Long id, String direccion, Ente dueño, TiposInmueble tiposInmueble) {
        this.id = id;
        this.direccion = direccion;
        this.dueño = dueño;
        this.tiposInmueble = tiposInmueble;

    }

}
