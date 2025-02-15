package com.tarea7.dgmt07e02.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tarea7.dgmt07e02.Domain.Departamento;

public interface DepartamentoRepository extends JpaRepository <Departamento, Long>{
    
}
