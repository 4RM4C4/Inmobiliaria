package com.house.entidades;

import com.house.Enumeraciones.TipoTransaccion;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Transacciones {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private Long id;
    private Inmueble inmueble;
    private String nombre;
    private TipoTransaccion tipoTransaccion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Inmueble getInmueble() {
        return inmueble;
    }

    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoTransaccion getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(TipoTransaccion tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public Transacciones(Long id, Inmueble inmueble, String nombre, TipoTransaccion tipoTransaccion) {
        this.id = id;
        this.inmueble = inmueble;
        this.nombre = nombre;
        this.tipoTransaccion = tipoTransaccion;
    }

    public Transacciones() {
    }
}
