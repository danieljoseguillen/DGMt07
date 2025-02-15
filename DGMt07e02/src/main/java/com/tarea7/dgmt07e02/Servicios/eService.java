package com.tarea7.dgmt07e02.Servicios;

import java.util.List;

import com.tarea7.dgmt07e02.Domain.Empleado;
import com.tarea7.dgmt07e02.Modelos.Genero;

public interface eService {

    List<Empleado>listAll();
    List<Empleado>listSorted(Genero genero,String val);
    Empleado getById(long id);
    Empleado agregar(Empleado empleado);
    boolean modificar(Empleado empleado);
    boolean borrarPorId(long id);
}
