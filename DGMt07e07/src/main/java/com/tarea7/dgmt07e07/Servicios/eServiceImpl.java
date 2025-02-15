package com.tarea7.dgmt07e07.Servicios;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tarea7.dgmt07e07.Domain.Empleado;
import com.tarea7.dgmt07e07.Domain.EmpleadoDTO;
import com.tarea7.dgmt07e07.Modelos.Genero;
import com.tarea7.dgmt07e07.Repositorios.EmpleadoRepository;

@Service
public class eServiceImpl implements eService {

    @Autowired
    EmpleadoRepository repositorio;

    @Autowired
    private ModelMapper modelMapper;

    public List<Empleado> listAll() {
        return repositorio.findAll();
    }

    public List<Empleado> listSorted(Genero genero, String val) {
        if (genero == null) {
            return repositorio.findByNombreContainingIgnoreCase(val);
        } else if (val.isEmpty() || val == null) {
            return repositorio.findByGenero(genero);
        } else {
            return repositorio.findByGeneroAndNombreContainingIgnoreCase(genero, val);
        }
    }
//Nuevo metodo
    public List<EmpleadoDTO> convertEmpleadoToDto(List<Empleado> listaEmpleados) {
        List<EmpleadoDTO> listaEmpleadoDTO = new ArrayList<>();
        for (Empleado empleado : listaEmpleados)
            listaEmpleadoDTO.add(modelMapper.map(empleado, EmpleadoDTO.class));
        return listaEmpleadoDTO;
    }

    public Empleado getById(long id) {
        return repositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontraron empleados con la ID indicada."));
    }

    public Empleado agregar(Empleado empleado) {
        try {
            return repositorio.save(empleado);
        } catch (Exception e) {
            throw new RuntimeException("No se pudo agregar el empleado.");
        }
    }

    public boolean modificar(Empleado empleado) {
        Empleado agr = repositorio.save(empleado);
        if (agr == null) {
            throw new RuntimeException("No se encontró el empleado a editar.");
        }
        return true;
    }

    public boolean borrarPorId(long id) {

        if (repositorio.findById(id) != null) {
            repositorio.deleteById(id);
            return true;
        }

        throw new RuntimeException("No existe el empleado.");
    }

}
