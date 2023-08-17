package com.house.repositorios;

import com.house.Enumeraciones.TiposInmueble;
import com.house.entidades.Inmueble;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InmuebleRepositorio extends JpaRepository<Inmueble, Long> {

    @Query("SELECT l FROM Inmueble l WHERE l.tiposInmueble = :tiposInmueble")
    public List<Inmueble> buscarPorTiposInmueble(@Param("tiposInmueble") TiposInmueble tiposInmueble);

}
