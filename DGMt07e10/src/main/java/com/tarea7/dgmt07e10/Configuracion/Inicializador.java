// package com.tarea7.dgmt07e10.Configuracion;

// import java.time.LocalDate;

// import org.springframework.boot.CommandLineRunner;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// import com.tarea7.dgmt07e10.Domain.PacienteConsulta;
// import com.tarea7.dgmt07e10.Domain.PacienteRecetas;
// import com.tarea7.dgmt07e10.Domain.PacienteRevision;
// import com.tarea7.dgmt07e10.Servicios.PatientService;

// @Configuration
// public class Inicializador {
//     // Clase que usé para agregar lso datos iniciales, comentada al pasarla a validate
//      @Bean
//    public CommandLineRunner initData(PatientService patientService) {
//       return args -> {
//          // Crear y agregar PacienteConsulta
//          PacienteConsulta pacienteConsulta1 = new PacienteConsulta();
//          pacienteConsulta1.setDni("12345678A");
//          pacienteConsulta1.setNombre("Juan Perez");
//          pacienteConsulta1.setFechaNacimiento(LocalDate.of(1980, 5, 10));
//          pacienteConsulta1.setMotivo("Consulta general");
//          patientService.agregarPaciente(pacienteConsulta1);

//          PacienteConsulta pacienteConsulta2 = new PacienteConsulta();
//          pacienteConsulta2.setDni("23456789B");
//          pacienteConsulta2.setNombre("Ana Ruiz");
//          pacienteConsulta2.setFechaNacimiento(LocalDate.of(1995, 3, 14));
//          pacienteConsulta2.setMotivo("Consulta especial");
//          patientService.agregarPaciente(pacienteConsulta2);

//          // Crear y agregar PacienteRecetas
//          PacienteRecetas pacienteRecetas1 = new PacienteRecetas();
//          pacienteRecetas1.setDni("34567890C");
//          pacienteRecetas1.setNombre("Laura Gómez");
//          pacienteRecetas1.setFechaNacimiento(LocalDate.of(1990, 8, 25));
//          pacienteRecetas1.setMedicamentos(3L);
//          patientService.agregarPaciente(pacienteRecetas1);

//          PacienteRecetas pacienteRecetas2 = new PacienteRecetas();
//          pacienteRecetas2.setDni("45678901D");
//          pacienteRecetas2.setNombre("Pedro Sánchez");
//          pacienteRecetas2.setFechaNacimiento(LocalDate.of(1987, 7, 30));
//          pacienteRecetas2.setMedicamentos(5L);
//          patientService.agregarPaciente(pacienteRecetas2);

//          // Crear y agregar PacienteRevision
//          PacienteRevision pacienteRevision1 = new PacienteRevision();
//          pacienteRevision1.setDni("56789012E");
//          pacienteRevision1.setNombre("Carlos Martínez");
//          pacienteRevision1.setFechaNacimiento(LocalDate.of(1955, 11, 2));
//          pacienteRevision1.setUltimaRevision(LocalDate.of(2022, 6, 15));
//          patientService.agregarPaciente(pacienteRevision1);

//          PacienteRevision pacienteRevision2 = new PacienteRevision();
//          pacienteRevision2.setDni("67890123F");
//          pacienteRevision2.setNombre("Maria López");
//          pacienteRevision2.setFechaNacimiento(LocalDate.of(1965, 2, 5));
//          pacienteRevision2.setUltimaRevision(LocalDate.of(2021, 9, 10));
//          patientService.agregarPaciente(pacienteRevision2);

//          // Confirmación en consola
//          System.out.println("Se agregaron 6 pacientes correctamente.");
//       };
//    }
// }
