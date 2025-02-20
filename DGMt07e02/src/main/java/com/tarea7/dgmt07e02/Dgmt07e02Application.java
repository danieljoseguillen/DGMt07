package com.tarea7.dgmt07e02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.context.annotation.Bean;

// import com.tarea7.dgmt07e02.Domain.Departamento;
// import com.tarea7.dgmt07e02.Domain.Empleado;
// import com.tarea7.dgmt07e02.Modelos.Estado;
// import com.tarea7.dgmt07e02.Modelos.Genero;
// import com.tarea7.dgmt07e02.Servicios.dService;
// import com.tarea7.dgmt07e02.Servicios.eService;

// import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class Dgmt07e02Application {

	public static void main(String[] args) {
		SpringApplication.run(Dgmt07e02Application.class, args);
	}

	// @Bean
	// CommandLineRunner initData(eService eServ,dService dServ){
	// 	return args -> {
	// 		Departamento depto1= dServ.agregar(new Departamento(null,"Informatica",null));
	// 		Departamento depto2= dServ.agregar(new Departamento(null,"RR.HH",null));
	// 		Departamento depto3= dServ.agregar(new Departamento(null,"Logistica",null));
	// 		eServ.agregar(new Empleado(null,"Jose","Jose@gmail.com",30000d,Estado.ACTIVO,Genero.MASCULINO,depto1));
	// 		eServ.agregar(new Empleado(null,"Pedro","pedrog@gmail.com",32000d,Estado.ACTIVO,Genero.MASCULINO,depto2));
	// 		eServ.agregar(new Empleado(null,"Maria","mcarolina@gmail.com",35000d,Estado.INACTIVO,Genero.FEMENINO,depto3));
	// 	};
	// }
}
