package com.tarea7.dgmt07e04.Repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tarea7.dgmt07e04.Domain.Nomina;

public interface NominaRepository extends JpaRepository <Nomina, Long>{
    List<Nomina> findByEmpleadoId(Long id);
}
