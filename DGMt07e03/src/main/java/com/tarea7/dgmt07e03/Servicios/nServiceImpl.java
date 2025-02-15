package com.tarea7.dgmt07e03.Servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.tarea7.dgmt07e03.Domain.Nomina;
import com.tarea7.dgmt07e03.Repositorios.NominaRepository;



@Service
public class nServiceImpl implements nService {

    @Autowired
    NominaRepository repositorio;

    public List<Nomina> listAll() {
        return repositorio.findAll();
    }

    public List<Nomina> listById(long id) {
        return repositorio.findByEmpleadoId(id);
    }
    public Nomina getById(long id) {
        return repositorio.findById(id).orElseThrow(()->new RuntimeException("No se encontraron nominas con la ID indicada."));
    }

    public Nomina agregar(Nomina nomina) {
        try {
            nomina.setImporteNeto(nomina.getImporteBruto()*(1-nomina.getPorcentImpuestos()/100));
            return repositorio.save(nomina);
        } catch(DataIntegrityViolationException e){
            throw new RuntimeException("Ya existe dicha nomina");
        }catch (Exception e) {
            throw new RuntimeException("No se pudo agregar la nomina.");
        }
    }

    public boolean modificar(Nomina nomina) {
        try {
            nomina.setImporteNeto(nomina.getImporteBruto()*(1-nomina.getPorcentImpuestos()/100));
            repositorio.save(nomina);
        }catch (Exception e) {
            throw new RuntimeException("No se pudo actualizar la nomina.");
        }
        return true;
    }

    public boolean borrarPorId(long id) {

            if (repositorio.findById(id) != null) {
                repositorio.deleteById(id);
                return true;
            }
        
        throw new RuntimeException("No existe la nomina.");
    }

}
