package com.tarea7.dgmt07e02b;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.context.annotation.Bean;

// import com.tarea7.dgmt07e02b.Domain.Departamento;
// import com.tarea7.dgmt07e02b.Domain.Empleado;
// import com.tarea7.dgmt07e02b.Modelos.Estado;
// import com.tarea7.dgmt07e02b.Modelos.Genero;
// import com.tarea7.dgmt07e02b.Servicios.dService;
// import com.tarea7.dgmt07e02b.Servicios.eService;

// import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class Dgmt07e02bApplication {

	public static void main(String[] args) {
		SpringApplication.run(Dgmt07e02bApplication.class, args);
	}

	// @Bean
	// CommandLineRunner initData(eService eServ,dService dServ){
	// 	return args -> {
	// 		Departamento depto1= dServ.agregar(new Departamento(null,"Informatica",80000d,null));
	// 		Departamento depto2= dServ.agregar(new Departamento(null,"RR.HH",80000d,null));
	// 		Departamento depto3= dServ.agregar(new Departamento(null,"Logistica",100000d,null));
	// 		eServ.agregar(new Empleado(null,"Jose","Jose@gmail.com",30000d,Estado.ACTIVO,Genero.MASCULINO,depto1));
	// 		eServ.agregar(new Empleado(null,"Pedro","pedrog@gmail.com",32000d,Estado.ACTIVO,Genero.MASCULINO,depto2));
	// 		eServ.agregar(new Empleado(null,"Maria","mcarolina@gmail.com",35000d,Estado.INACTIVO,Genero.FEMENINO,depto3));
	// 	};
	// }
}
