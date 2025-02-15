package com.tarea7.dgmt07e04.Servicios;

import java.util.List;

import com.tarea7.dgmt07e04.Domain.Integrante;

public interface iService {

    List<Integrante> getByEmpleado(long id);

    List<Integrante> getByProyecto(long id);

    Integrante getById(long id);

    Integrante agregar(Integrante colaboracion);

    boolean borrarPorId(long id);
}
