package com.tarea7.dgmt07e04.Servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.tarea7.dgmt07e04.Domain.Proyecto;
import com.tarea7.dgmt07e04.Repositorios.ProyectoRepository;



@Service
public class pServiceImpl implements pService {

    @Autowired
    ProyectoRepository repositorio;

    public List<Proyecto> listAll() {
        return repositorio.findAll();
    }

    public Proyecto getById(long id) {
        return repositorio.findById(id).orElseThrow(()->new RuntimeException("No se encontraron proyectos con la ID indicada."));
    }

    public Proyecto agregar(Proyecto proyecto) {
        try {
            return repositorio.save(proyecto);
        } catch(DataIntegrityViolationException e){
            throw new RuntimeException("Ya existe dicho proyecto");
        }catch (Exception e) {
            throw new RuntimeException("No se pudo agregar el proyecto.");
        }
    }

    public boolean modificar(Proyecto proyecto) {
        try {
            repositorio.save(proyecto);
        }catch (Exception e) {
            throw new RuntimeException("No se pudo actualizar el proyecto.");
        }
        return true;
    }

    public boolean borrarPorId(long id) {

            if (repositorio.findById(id).isPresent()) {
                repositorio.deleteById(id);
                return true;
            }
        
        throw new RuntimeException("No existe el proyecto.");
    }

}
