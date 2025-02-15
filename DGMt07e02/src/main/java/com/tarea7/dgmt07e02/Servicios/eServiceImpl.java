package com.tarea7.dgmt07e02.Servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tarea7.dgmt07e02.Domain.Empleado;
import com.tarea7.dgmt07e02.Modelos.Genero;
import com.tarea7.dgmt07e02.Repositorios.EmpleadoRepository;

@Service
public class eServiceImpl implements eService {

    @Autowired
    EmpleadoRepository repositorio;

    public List<Empleado> listAll() {
        return repositorio.findAll();
    }

    public List<Empleado> listSorted(Genero genero,String val) {
        if (genero==null) {
            return repositorio.findByNombreContainingIgnoreCase(val);
        }else if(val.isEmpty() || val==null){
            return repositorio.findByGenero(genero);
        }else{
            return repositorio.findByGeneroAndNombreContainingIgnoreCase(genero, val);
        }
    }

    public List<Empleado> listGender(Genero genero) {
        return repositorio.findByGenero(genero);
    }

    public Empleado getById(long id) {
        return repositorio.findById(id).orElseThrow(()->new RuntimeException("No se encontraron empleados con la ID indicada."));
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
