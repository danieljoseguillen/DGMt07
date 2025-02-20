package com.tarea7.dgmt07e07.Servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tarea7.dgmt07e07.Domain.Departamento;
import com.tarea7.dgmt07e07.Repositorios.DepartamentoRepository;


@Service
public class dServiceImpl implements dService {

    @Autowired
    DepartamentoRepository repositorio;

    public List<Departamento> listAll() {
        return repositorio.findAll();
    }

    public Departamento getById(long id) {
        return repositorio.findById(id).orElseThrow(()->new RuntimeException("No se encontraron departamentos con la ID indicada."));
    }

    public Departamento agregar(Departamento departamento) {
        try {
            return repositorio.save(departamento);
        } catch (Exception e) {
            throw new RuntimeException("No se pudo agregar el departamento.");
        }
    }

    public boolean modificar(Departamento departamento) {
        try {
            repositorio.save(departamento);
        }catch (Exception e) {
            throw new RuntimeException("No se pudo modificar el departamento.");
        }
        return true;
    }

    public boolean borrarPorId(long id) {

            if (repositorio.findById(id).isPresent()) {
                repositorio.deleteById(id);
                return true;
            }
        
        throw new RuntimeException("No existe el departamento.");
    }

}
