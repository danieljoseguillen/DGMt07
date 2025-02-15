package com.tarea7.dgmt07e06.Repositorios;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tarea7.dgmt07e06.Domain.Empleado;

public interface EmpleadoRepository extends JpaRepository <Empleado, Long> {
    List<Empleado> findByNombreContainingIgnoreCase(String nombre);
}
