package com.tarea7.dgmt07e02b.Servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.tarea7.dgmt07e02b.Domain.Departamento;
import com.tarea7.dgmt07e02b.Repositorios.DepartamentoRepository;


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
        } catch(DataIntegrityViolationException e){
            throw new RuntimeException("Ya existe un departamento con ese nombre");
        }catch (Exception e) {
            throw new RuntimeException("No se pudo agregar el departamento.");
        }
    }

    public boolean modificar(Departamento departamento) {
        Departamento agr = repositorio.save(departamento);
        if (agr == null) {
            throw new RuntimeException("No se encontró el departamento a editar.");
        }
        return true;
    }

    public boolean borrarPorId(long id) {

            if (repositorio.findById(id) != null) {
                repositorio.deleteById(id);
                return true;
            }
        
        throw new RuntimeException("No existe el departamento.");
    }

}
