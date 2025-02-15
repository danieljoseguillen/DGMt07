package com.tarea7.dgmt07e07.Servicios;

import java.util.List;

import com.tarea7.dgmt07e07.Domain.Empleado;
import com.tarea7.dgmt07e07.Domain.EmpleadoDTO;
import com.tarea7.dgmt07e07.Modelos.Genero;

public interface eService {

    List<Empleado>listAll();
    List<Empleado>listSorted(Genero genero,String val);
    List<EmpleadoDTO> convertEmpleadoToDto(List<Empleado> listaEmpleados);
    Empleado getById(long id);
    Empleado agregar(Empleado empleado);
    boolean modificar(Empleado empleado);
    boolean borrarPorId(long id);
}
