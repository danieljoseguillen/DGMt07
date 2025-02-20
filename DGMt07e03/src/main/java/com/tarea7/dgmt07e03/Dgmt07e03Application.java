package com.tarea7.dgmt07e03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// import java.time.LocalDate;
// import java.util.Arrays;
// import java.util.List;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.context.annotation.Bean;
// import com.tarea7.dgmt07e03.Domain.Departamento;
// import com.tarea7.dgmt07e03.Domain.Empleado;
// import com.tarea7.dgmt07e03.Domain.Nomina;
// import com.tarea7.dgmt07e03.Modelos.Estado;
// import com.tarea7.dgmt07e03.Modelos.Genero;
// import com.tarea7.dgmt07e03.Servicios.dService;
// import com.tarea7.dgmt07e03.Servicios.eService;
// import com.tarea7.dgmt07e03.Servicios.nService;

@SpringBootApplication
public class Dgmt07e03Application {

	public static void main(String[] args) {
		SpringApplication.run(Dgmt07e03Application.class, args);
	}

	// @Bean
	// CommandLineRunner initData(eService eServ, dService dServ, nService nServ) {
	// 	return args -> {
	// 		Departamento depto1 = dServ.agregar(new Departamento(null, "Informatica", 80000d, null));
	// 		Departamento depto2 = dServ.agregar(new Departamento(null, "RR.HH", 80000d, null));
	// 		Departamento depto3 = dServ.agregar(new Departamento(null, "Logistica", 100000d, null));
	// 		Empleado emp1 = eServ.agregar(new Empleado(null, "Jose", "Jose@gmail.com", 30000d, Estado.ACTIVO,
	// 				Genero.MASCULINO, depto1, null));
	// 		Empleado emp2 = eServ.agregar(new Empleado(null, "Pedro", "pedrog@gmail.com", 32000d, Estado.ACTIVO,
	// 				Genero.MASCULINO, depto2, null));
	// 		Empleado emp3 = eServ.agregar(new Empleado(null, "Maria", "mcarolina@gmail.com", 35000d, Estado.INACTIVO,
	// 				Genero.FEMENINO, depto3, null));
	// 		List<Empleado> empleadosActivos = Arrays.asList(emp1, emp2);
	// 		LocalDate fechaInicio = LocalDate.of(2024, 1, 1);

	// 		for (int i = 0; i < 12; i++) {
	// 			Empleado empleado = empleadosActivos.get(i % empleadosActivos.size());
	// 			Double salarioBase = empleado.getSalario();
	// 			Double impuestos = 15d; // 15% de impuestos
	// 			Double importeBruto = salarioBase;
	// 			Double importeNeto = importeBruto * (1 - impuestos);

	// 			Nomina nomina = new Nomina(null, fechaInicio.plusMonths(i), importeBruto, impuestos, importeNeto,
	// 					empleado);
	// 			nServ.agregar(nomina);
	// 		}
	// 	};
	// }
}
