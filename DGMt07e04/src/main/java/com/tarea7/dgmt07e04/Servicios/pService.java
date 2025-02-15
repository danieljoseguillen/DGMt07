package com.tarea7.dgmt07e04.Servicios;

import java.util.List;

import com.tarea7.dgmt07e04.Domain.Proyecto;

public interface pService {

    List<Proyecto>listAll();
    Proyecto getById(long id);
    Proyecto agregar(Proyecto proyecto);
    boolean modificar(Proyecto proyecto);
    boolean borrarPorId(long id);
}
