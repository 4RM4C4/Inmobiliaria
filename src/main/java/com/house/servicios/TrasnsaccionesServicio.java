package com.house.servicios;

import com.house.Enumeraciones.TipoTransaccion;
import com.house.excepciones.MiException;
import com.house.entidades.Inmueble;
import com.house.entidades.Transacciones;
import com.house.repositorios.InmuebleRepositorio;
import com.house.repositorios.TransaccionesRepositorio;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrasnsaccionesServicio {

    @Autowired
    private InmuebleRepositorio inmuebleRepositorio;
    @Autowired
    private TransaccionesRepositorio transaccionesRepositorio;

    @Transactional
    public void crearTransacciones(Long id, Long idInmueble, String nombre, String tipoTransaccion) throws MiException {
        validar(id, idInmueble, nombre, tipoTransaccion);
        Optional<Transacciones> respuesta = transaccionesRepositorio.findById(id);
        Optional<Inmueble> respuestaInmueble = inmuebleRepositorio.findById(idInmueble);
        Transacciones transacciones = new Transacciones();
        Inmueble inmueble = new Inmueble();
        if (respuestaInmueble.isPresent()) {
            inmueble = respuestaInmueble.get();
        }
        transacciones.setId(id);
        transacciones.setInmueble(inmueble);
        transacciones.setNombre(nombre);

        TipoTransaccion[] validar = TipoTransaccion.getValues();
        TipoTransaccion tipoTransaccionEnum = null;
        for (TipoTransaccion tipo : validar) {
            if (tipo.name().equalsIgnoreCase(tipoTransaccion)) {
                tipoTransaccionEnum = tipo;
            }
        }
        transacciones.setTipoTransaccion(tipoTransaccionEnum);
        transaccionesRepositorio.save(transacciones);
    }

    @Transactional
    public void modificarTransacciones(Long id, Long idInmueble, String nombre, String tipoTransaccion) throws MiException {
        validar(id, idInmueble, nombre, tipoTransaccion);
        Optional<Transacciones> respuesta = transaccionesRepositorio.findById(id);
        Optional<Inmueble> respuestaInmueble = inmuebleRepositorio.findById(idInmueble);
        Inmueble inmueble = new Inmueble();
        if (respuestaInmueble.isPresent()) {
            inmueble = respuestaInmueble.get();
        }
        if (respuesta.isPresent()) {
            Transacciones transacciones = new Transacciones();
            transacciones.setId(id);
            transacciones.setInmueble(inmueble);
            transacciones.setNombre(nombre);
            TipoTransaccion[] validar = TipoTransaccion.getValues();
            TipoTransaccion tipoTransaccionEnum = null;
            for (TipoTransaccion tipo : validar) {
                if (tipo.name().equalsIgnoreCase(tipoTransaccion)) {
                    tipoTransaccionEnum = tipo;
                }
            }
            transacciones.setTipoTransaccion(tipoTransaccionEnum);
            transaccionesRepositorio.save(transacciones);

        }

    }

    public List<Transacciones> listarTransacciones() {
        List<Transacciones> transacciones = new ArrayList();
        transacciones = transaccionesRepositorio.findAll();
        return transacciones;
    }

    public Transacciones getOne(Long id) {
        return transaccionesRepositorio.getOne(id);
    }

    private void validar(Long id, Long idInmueble, String nombre, String tipoTransaccion) throws MiException {
        if (id == null) {
            throw new MiException("Id no puede ser nulo");
        }
        if (idInmueble == null) {
            throw new MiException("Id inmueble no puede ser nulo");
        }
        if (nombre.isEmpty() || nombre == null) {
            throw new MiException("nombre no puede ser nulo");
        }

        boolean validacionTipoTransaccion = true;
        TipoTransaccion[] validar = TipoTransaccion.getValues();
        for (TipoTransaccion tipo : validar) {
            if (tipo.name().equalsIgnoreCase(tipoTransaccion)) {
                validacionTipoTransaccion = false;
            }
        }
        if (validacionTipoTransaccion) {
            throw new MiException("El tipo de transaccion no es válido");
        }

    }
}
