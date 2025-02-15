package com.tarea7.dgmt07e06.Servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tarea7.dgmt07e06.Domain.Empleado;
import com.tarea7.dgmt07e06.Repositorios.EmpleadoRepository;

@Service
public class eServiceImpl implements eService {
    private final Integer pageSize = 10;
    @Autowired
    EmpleadoRepository repositorio;

    public List<Empleado> listAll() {
        return repositorio.findAll();
    }

    public List<Empleado> listSorted(String val) {
        return repositorio.findByNombreContainingIgnoreCase(val);
    }

    public List<Empleado> listPaged(Integer pageNum) {
        Pageable paging = PageRequest.of(pageNum, pageSize, Sort.by("id"));
        Page<Empleado> pagedResult = repositorio.findAll(paging);
        if (pagedResult.hasContent())
            return pagedResult.getContent();
        else
            return null;
    }

    public Empleado getById(long id) {
        return repositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontraron empleados con la ID indicada."));
    }

    public int getTotalPaginas() {
        Pageable paging = PageRequest.of(0, pageSize, Sort.by("id"));
        Page<Empleado> pagedResult = repositorio.findAll(paging);
        return pagedResult.getTotalPages();
    }
}
