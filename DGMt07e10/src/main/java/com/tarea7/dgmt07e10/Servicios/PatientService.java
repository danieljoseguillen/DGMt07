package com.tarea7.dgmt07e10.Servicios;

import java.util.List;

import com.tarea7.dgmt07e10.Domain.Paciente;
import com.tarea7.dgmt07e10.Domain.PacienteDTO;

public interface PatientService {
    void agregarPaciente(Paciente paciente);
    void deleteFirst();
    Paciente getFirst();
    List<Paciente> listAll();
    Paciente buildFromDTO(PacienteDTO pacienteDTO);
    Double facturar (Paciente paciente);
}
