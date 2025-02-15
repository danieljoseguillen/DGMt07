package com.tarea7.dgmt07e04.Repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tarea7.dgmt07e04.Domain.Integrante;

public interface IntegranteRepository extends JpaRepository <Integrante, Long>{

    List<Integrante> findByEmpleadoId(long empleado);
    List<Integrante> findByProyectoId(long proyecto);
}
