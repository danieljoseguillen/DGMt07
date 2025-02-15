package com.tarea7.dgmt07e04.Servicios;

import java.util.List;

import com.tarea7.dgmt07e04.Domain.Nomina;

public interface nService {

    List<Nomina>listAll();
    List<Nomina>listById(long id);
    Nomina getById(long id);
    Nomina agregar(Nomina nomina);
    boolean modificar(Nomina nomina);
    boolean borrarPorId(long id);
}
