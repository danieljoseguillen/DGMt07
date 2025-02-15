package com.tarea7.dgmt07e03.Servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tarea7.dgmt07e03.Domain.Departamento;
import com.tarea7.dgmt07e03.Domain.Empleado;
import com.tarea7.dgmt07e03.Modelos.Genero;
import com.tarea7.dgmt07e03.Repositorios.DepartamentoRepository;
import com.tarea7.dgmt07e03.Repositorios.EmpleadoRepository;

@Service
public class eServiceImpl implements eService {

    @Autowired
    EmpleadoRepository repositorio;

    @Autowired
    DepartamentoRepository deptoRepository;

    public List<Empleado> listAll() {
        return repositorio.findAll();
    }

    public List<Empleado> listSorted(Genero genero, String val, Long depto) {
        // En caso de ser val null lo cambia a vacio, así puede usarse el metodo con
        // like y se evitan errores.
        if (val == null)
            val = "";
        // Verifica que valores se seleccionaron en los campos de busqueda y aplica la
        // funcion/metodo adecuado
        if (depto == 0 && genero == null) {
            return repositorio.findByNombreContainingIgnoreCase(val);
        } else if (genero == null) {
            return repositorio.findByDepartamentoIdAndNombreContainingIgnoreCase(depto, val);
        } else if (depto == 0) {
            return repositorio.findByGeneroAndNombreContainingIgnoreCase(genero, val);
        } else {
            return repositorio.findByGeneroAndDepartamentoIdAndNombreContainingIgnoreCase(genero, depto, val);
        }
    }

    public List<Empleado> listGender(Genero genero) {
        return repositorio.findByGenero(genero);
    }

    public Empleado getById(long id) {
        return repositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontraron empleados con la ID indicada."));
    }

    public Empleado agregar(Empleado empleado) {
        // Depatamento del que se sacarán los datos
        Departamento depto = empleado.getDepartamento();
        // Validación (me dió error en la carga antes)
        Double total = repositorio.sumasalarioDepto(depto.getId());
        if (total == null) {
            total = 0d;
        }
        total += empleado.getSalario();
        // Verificación de presupuesto
        if (total > depto.getPresupuesto()) {
            throw new RuntimeException(
                    "El salario total de los empleados supera el presupuesto anual del departamento.");
        }
        // control de erorres
        try {
            return repositorio.save(empleado);
        } catch (Exception e) {
            throw new RuntimeException("No se pudo agregar el empleado.");
        }
    }

    public boolean modificar(Empleado empleado) {
        // Depatamento del que se sacarán los datos
        Departamento depto = empleado.getDepartamento();
        // Busca el empleado antes de la modificación
        Empleado oldEmp = repositorio.findById(empleado.getId())
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado."));
        // Validación contra nulos
        Double total = repositorio.sumasalarioDepto(depto.getId());
        if (total == null) {
            total = 0d;
        }
        //Verifica en caso de cambios de departamento, para cambiar la formula
        if (oldEmp.getDepartamento() != empleado.getDepartamento()) {
            total = total + empleado.getSalario();
        } else {
            total = total - oldEmp.getSalario() + empleado.getSalario();
        }
        System.out.println(depto.getNombre());
        System.out.println("TOTAL: " + total);
        // Verificación de presupuesto
        if (total > depto.getPresupuesto()) {
            throw new RuntimeException(
                    "El salario total de los empleados supera el presupuesto anual del departamento.");
        }
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
