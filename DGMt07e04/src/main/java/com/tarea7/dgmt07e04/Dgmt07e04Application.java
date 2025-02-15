package com.tarea7.dgmt07e04;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.tarea7.dgmt07e04.Domain.Departamento;
import com.tarea7.dgmt07e04.Domain.Empleado;
import com.tarea7.dgmt07e04.Domain.Integrante;
import com.tarea7.dgmt07e04.Domain.Nomina;
import com.tarea7.dgmt07e04.Domain.Proyecto;
import com.tarea7.dgmt07e04.Modelos.Estado;
import com.tarea7.dgmt07e04.Modelos.Genero;
import com.tarea7.dgmt07e04.Servicios.dService;
import com.tarea7.dgmt07e04.Servicios.eService;
import com.tarea7.dgmt07e04.Servicios.iService;
import com.tarea7.dgmt07e04.Servicios.nService;
import com.tarea7.dgmt07e04.Servicios.pService;

@SpringBootApplication
public class Dgmt07e04Application {

	public static void main(String[] args) {
		SpringApplication.run(Dgmt07e04Application.class, args);
	}

	@Bean
	CommandLineRunner initData(eService eServ, dService dServ, nService nServ, pService pServ, iService iServ) {
		return args -> {
			Departamento depto1 = dServ.agregar(new Departamento(null, "Informatica", 80000d, null));
			Departamento depto2 = dServ.agregar(new Departamento(null, "RR.HH", 80000d, null));
			Departamento depto3 = dServ.agregar(new Departamento(null, "Logistica", 100000d, null));
			Empleado emp1 = eServ.agregar(new Empleado(null, "Jose", "Jose@gmail.com", 30000d, Estado.ACTIVO,
					Genero.MASCULINO, depto1, null, null));
			Empleado emp2 = eServ.agregar(new Empleado(null, "Pedro", "pedrog@gmail.com", 32000d, Estado.ACTIVO,
					Genero.MASCULINO, depto2, null, null));
			Empleado emp3 = eServ.agregar(new Empleado(null, "Maria", "mcarolina@gmail.com", 35000d, Estado.INACTIVO,
					Genero.FEMENINO, depto3, null, null));
			Empleado emp4 = eServ.agregar(new Empleado(null, "Paula", "pmendoza@gmail.com", 34000d, Estado.ACTIVO,
					Genero.FEMENINO, depto3, null, null));

			Proyecto p1 = pServ.agregar(new Proyecto(null, "Despliegue de pagina web estatal", null));
			Proyecto p2 = pServ.agregar(new Proyecto(null, "Gestion de incidencia n34", null));
			Proyecto p3 = pServ.agregar(new Proyecto(null, "Automatización de procesos internos", null));
			List<Empleado> empleadosActivos = Arrays.asList(emp1, emp2, emp4);
			LocalDate fechaInicio = LocalDate.of(2024, 1, 1);

			for (int i = 0; i < 12; i++) {
				Empleado empleado = empleadosActivos.get(i % empleadosActivos.size());
				Double salarioBase = empleado.getSalario();
				Double impuestos = 15d; // 15% de impuestos
				Double importeBruto = salarioBase;
				Double importeNeto = importeBruto * (1 - impuestos);

				Nomina nomina = new Nomina(null, fechaInicio.plusMonths(i), importeBruto, impuestos, importeNeto,
						empleado);
				nServ.agregar(nomina);
			}

			iServ.agregar(new Integrante(null, emp1, p1, "Desarrollador"));
			iServ.agregar(new Integrante(null, emp2, p1, "Tester"));
			iServ.agregar(new Integrante(null, emp4, p1, "Líder de proyecto"));

			iServ.agregar(new Integrante(null, emp1, p2, "Analista de requerimientos"));
			iServ.agregar(new Integrante(null, emp2, p2, "Desarrollador Backend"));

			iServ.agregar(new Integrante(null, emp1, p3, "Arquitecto de software"));
			iServ.agregar(new Integrante(null, emp4, p3, "Scrum Master"));
		};
	}
}
