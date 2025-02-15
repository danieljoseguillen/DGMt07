package com.tarea7.dgmt07e02b.Servicios;

import java.util.List;

import com.tarea7.dgmt07e02b.Domain.Empleado;
import com.tarea7.dgmt07e02b.Modelos.Genero;

public interface eService {

    List<Empleado>listAll();
    List<Empleado>listSorted(Genero genero,String val, Long depto);
    Empleado getById(long id);
    Empleado agregar(Empleado empleado);
    boolean modificar(Empleado empleado);
    boolean borrarPorId(long id);
}
