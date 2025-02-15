package com.tarea7.dgmt07e06.Servicios;

import java.util.List;

import com.tarea7.dgmt07e06.Domain.Empleado;

public interface eService {

    List<Empleado> listAll();

    List<Empleado> listSorted(String val);

    Empleado getById(long id);

    List<Empleado> listPaged(Integer pageNum);

    int getTotalPaginas();
}
