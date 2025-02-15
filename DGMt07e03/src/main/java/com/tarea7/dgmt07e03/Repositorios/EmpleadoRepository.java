package com.tarea7.dgmt07e03.Repositorios;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tarea7.dgmt07e03.Domain.Empleado;
import com.tarea7.dgmt07e03.Modelos.Genero;

public interface EmpleadoRepository extends JpaRepository <Empleado, Long> {
    List<Empleado> findByGenero(Genero genero);
    // Metodos para organizar
    List<Empleado> findByNombreContainingIgnoreCase(String nombre);
    List<Empleado> findByDepartamentoIdAndNombreContainingIgnoreCase(Long depto, String nombre);
    List<Empleado> findByGeneroAndNombreContainingIgnoreCase(Genero genero, String nombre);
    List<Empleado> findByGeneroAndDepartamentoIdAndNombreContainingIgnoreCase(Genero genero, Long depto, String nombre);
    // Metodo para el total de salarios
    @Query("select sum(e.salario) from Empleado e where e.departamento.id=:deptoID")
    Double sumasalarioDepto(@Param("deptoID") Long deptoID);
}
