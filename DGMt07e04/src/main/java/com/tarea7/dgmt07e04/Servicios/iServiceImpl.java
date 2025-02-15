package com.tarea7.dgmt07e04.Servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.tarea7.dgmt07e04.Domain.Integrante;
import com.tarea7.dgmt07e04.Repositorios.IntegranteRepository;

@Service
public class iServiceImpl implements iService {

    @Autowired
    IntegranteRepository repositorio;

    public List<Integrante> getByEmpleado(long id) {
        return repositorio.findByEmpleadoId(id);
    }

    public List<Integrante> getByProyecto(long id) {
        return repositorio.findByProyectoId(id);
    }

    public Integrante getById(long id) {
        return repositorio.findById(id).orElseThrow(()->new RuntimeException("No se encontraron integrantes con el ID indicada."));
    }

    public Integrante agregar(Integrante integrante) {
        try {
            return repositorio.save(integrante);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Error de datos");
        } catch (Exception e) {
            throw new RuntimeException("No se pudo agregar el integrante.");
        }
    }

    public boolean borrarPorId(long id) {

        if (repositorio.findById(id) != null) {
            repositorio.deleteById(id);
            return true;
        }

        throw new RuntimeException("No existe el integrante.");
    }

}
