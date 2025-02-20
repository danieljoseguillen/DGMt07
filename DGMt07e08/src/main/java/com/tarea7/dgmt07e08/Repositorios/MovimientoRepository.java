package com.tarea7.dgmt07e08.Repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tarea7.dgmt07e08.Domain.Movimiento;

public interface MovimientoRepository extends JpaRepository<Movimiento,Long>{
    List<Movimiento> findAllByOrderByFechaHoraDesc();
    List<Movimiento> findByCuentaIBANOrderByFechaHoraDesc(String IBAN);
}
