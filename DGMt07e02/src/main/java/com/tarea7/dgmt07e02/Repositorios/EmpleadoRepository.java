package com.tarea7.dgmt07e02.Repositorios;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tarea7.dgmt07e02.Domain.Empleado;
import com.tarea7.dgmt07e02.Modelos.Genero;

public interface EmpleadoRepository extends JpaRepository <Empleado, Long> {
    List<Empleado> findByGenero(Genero genero);
    List<Empleado> findByNombreContainingIgnoreCase(String nombre);
    List<Empleado> findByGeneroAndNombreContainingIgnoreCase(Genero genero, String nombre);

}
