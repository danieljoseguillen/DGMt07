package com.tarea7.dgmt07e02b.Servicios;

import java.util.List;

import com.tarea7.dgmt07e02b.Domain.Departamento;

public interface dService {

    List<Departamento>listAll();
    Departamento getById(long id);
    Departamento agregar(Departamento departamento);
    boolean modificar(Departamento departamento);
    boolean borrarPorId(long id);
}
