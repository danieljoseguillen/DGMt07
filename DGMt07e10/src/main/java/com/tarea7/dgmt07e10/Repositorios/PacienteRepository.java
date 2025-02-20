package com.tarea7.dgmt07e10.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tarea7.dgmt07e10.Domain.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente,Long>{
    
    @Query("select p from Paciente p where p.id=(select min(p2.id) from Paciente p2)")
    Paciente findMinPaciente();
}
