package com.house.servicios;

import com.house.Enumeraciones.TiposInmueble;
import com.house.excepciones.MiException;
import com.house.entidades.Ente;
import com.house.entidades.Inmueble;
import com.house.repositorios.EnteRepositorio;
import com.house.repositorios.InmuebleRepositorio;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InmuebleServicio {

    @Autowired
    private InmuebleRepositorio inmuebleRepositorio;
    @Autowired
    private EnteRepositorio enteRepositorio;

    @Transactional
    public void crearInmueble(Long id, String direccion, String idDueño, String tiposInmueble) throws MiException {
        validar(id, direccion, idDueño, tiposInmueble);
        Optional<Inmueble> respuesta = inmuebleRepositorio.findById(id);
        Optional<Ente> respuestaEnte = enteRepositorio.findById(idDueño);
        Ente ente = new Ente();
        if (respuestaEnte.isPresent()) {
            ente = respuestaEnte.get();
        }
        Inmueble inmueble = new Inmueble();
        inmueble.setDireccion(direccion);
        inmueble.setDueño(ente);
        inmueble.setId(id);
        TiposInmueble[] validar = TiposInmueble.getValues();
        TiposInmueble tipoInmuebleEnum = null;
        for (TiposInmueble tipo : validar) {
            if (tipo.name().equalsIgnoreCase(tiposInmueble)) {
                tipoInmuebleEnum = tipo;
            }
        }
        inmueble.setTiposInmueble(tipoInmuebleEnum);
    }

    public List<Inmueble> listarInmuebles() {
        List<Inmueble> inmuebles = new ArrayList();
        inmuebles = inmuebleRepositorio.findAll();
        return inmuebles;
    }

    @Transactional
    public void modificarInmueble(Long id, String direccion, String idDueño, String tiposInmueble) throws MiException {
        validar(id, direccion, idDueño, tiposInmueble);
        Optional<Inmueble> respuesta = inmuebleRepositorio.findById(id);
        Optional<Ente> respuestaEnte = enteRepositorio.findById(idDueño);
        Ente ente = new Ente();
        if (respuestaEnte.isPresent()) {
            ente = respuestaEnte.get();
        }
        if (respuesta.isPresent()) {
            Inmueble inmueble = respuesta.get();
            inmueble.setDireccion(direccion);
            inmueble.setDueño(ente);
            TiposInmueble[] validar = TiposInmueble.getValues();
            TiposInmueble tipoInmuebleEnum = null;
            for (TiposInmueble tipo : validar) {
                if (tipo.name().equalsIgnoreCase(tiposInmueble)) {
                    tipoInmuebleEnum = tipo;
                }
            }
            inmueble.setTiposInmueble(tipoInmuebleEnum);
            inmuebleRepositorio.save(inmueble);
        }
    }

    public Inmueble getOne(Long id) {
        return inmuebleRepositorio.getOne(id);
    }

    private void validar(Long id, String direccion, String idDueño, String tiposInmueble) throws MiException {
        if (id == null) {
            throw new MiException("Id no puede ser nulo");
        }
        if (direccion.isEmpty() || direccion == null) {
            throw new MiException("Direccion no puede ser nulo");
        }
        if (idDueño.isEmpty() || idDueño == null) {
            throw new MiException("Direccion no puede ser nulo");
        }

        boolean validacionTiposInmueble = true;
        TiposInmueble[] validar = TiposInmueble.getValues();
        for (TiposInmueble tipo : validar) {
            if (tipo.name().equalsIgnoreCase(tiposInmueble)) {
                validacionTiposInmueble = false;
            }
        }
        if (validacionTiposInmueble) {
            throw new MiException("El tipo de Inmueble no es válido");
        }

    }

}
