package com.tarea7.dgmt07e10.Servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tarea7.dgmt07e10.Configuracion.Tarifas;
import com.tarea7.dgmt07e10.Domain.Paciente;
import com.tarea7.dgmt07e10.Domain.PacienteConsulta;
import com.tarea7.dgmt07e10.Domain.PacienteDTO;
import com.tarea7.dgmt07e10.Domain.PacienteRecetas;
import com.tarea7.dgmt07e10.Domain.PacienteRevision;
import com.tarea7.dgmt07e10.Repositorios.PacienteRepository;

@Service
public class PatientServiceImpl implements PatientService {

   @Autowired
   private Tarifas tarifas;

   @Autowired
   private PacienteRepository repository;

   // LinkedList<Paciente> repositorio = new LinkedList<>();
   public void agregarPaciente(Paciente paciente) {
      repository.save(paciente);
      // repositorio.add(paciente);
   }
   public void deleteFirst() {
      if (repository.count() ==0) {
         throw new RuntimeException("La lista está vacía.");
      }
      repository.delete(repository.findMinPaciente());
      // repositorio.removeFirst();
   }

   public Double facturar (Paciente paciente){
      if (paciente != null) {
         return paciente.facturar(tarifas);
      }
      throw new RuntimeException("Ha ocurrido un error al procesar la factura.");
      }

   public Paciente getFirst() {
      if (repository.count() ==0) {
         throw new RuntimeException("La lista está vacía.");
      }
      return repository.findMinPaciente();
      // return repositorio.getFirst();
   }

   public List<Paciente> listAll() {
      System.out.println(repository.count());
       return repository.findAll();
      // System.out.println(repositorio.size());
      // return repositorio;
   }

   public Paciente buildFromDTO(PacienteDTO pacienteDTO) {
      Paciente paciente;
      switch (pacienteDTO.getTipoPaciente()) {
         case 1 -> {
            paciente = new PacienteConsulta();
            ((PacienteConsulta) paciente).setMotivo(pacienteDTO.getMotivo());
         }
         case 2 -> {
            paciente = new PacienteRecetas();
            Long meds = pacienteDTO.getMedicamentos();
            ((PacienteRecetas) paciente).setMedicamentos(meds);
            System.out.println(((PacienteRecetas) paciente).getMedicamentos());
         }
         case 3 -> {
            paciente = new PacienteRevision();
            ((PacienteRevision) paciente).setUltimaRevision(pacienteDTO.getUltimaRevision());
         }
         default -> {
            throw new RuntimeException("Ocurrió un error al crear el paciente.");
         }
      }
      paciente.setDni(pacienteDTO.getDni());
      paciente.setNombre(pacienteDTO.getNombre());
      paciente.setFechaNacimiento(pacienteDTO.getFechaNacimiento());
      System.out.println(paciente.getClass());
      return paciente;
   }

}
