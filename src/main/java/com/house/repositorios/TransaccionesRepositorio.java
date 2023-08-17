package com.house.repositorios;

import com.house.entidades.Transacciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransaccionesRepositorio extends JpaRepository<Transacciones, Long> {

}
