package com.tarea7.dgmt07e04.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tarea7.dgmt07e04.Domain.Departamento;

public interface DepartamentoRepository extends JpaRepository <Departamento, Long>{
    
}
