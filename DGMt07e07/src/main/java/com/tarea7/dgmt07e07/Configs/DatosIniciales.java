// package com.tarea7.dgmt07e07.Configs;

// import org.springframework.boot.CommandLineRunner;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// import com.tarea7.dgmt07e07.Domain.Departamento;
// import com.tarea7.dgmt07e07.Domain.Empleado;
// import com.tarea7.dgmt07e07.Modelos.Estado;
// import com.tarea7.dgmt07e07.Modelos.Genero;
// import com.tarea7.dgmt07e07.Servicios.dService;
// import com.tarea7.dgmt07e07.Servicios.eService;

// @Configuration
// public class DatosIniciales {
//     	@Bean
// 	CommandLineRunner initData(eService eServ,dService dServ){
// 		return args -> {
// 			Departamento depto1= dServ.agregar(new Departamento(null,"Informatica",null));
// 			Departamento depto2= dServ.agregar(new Departamento(null,"RR.HH",null));
// 			Departamento depto3= dServ.agregar(new Departamento(null,"Logistica",null));
// 			eServ.agregar(new Empleado(null,"Jose","Jose@gmail.com",30000d,Estado.ACTIVO,Genero.MASCULINO,depto1));
// 			eServ.agregar(new Empleado(null,"Pedro","pedrog@gmail.com",32000d,Estado.ACTIVO,Genero.MASCULINO,depto2));
// 			eServ.agregar(new Empleado(null,"Maria","mcarolina@gmail.com",35000d,Estado.INACTIVO,Genero.FEMENINO,depto3));
// 		};
// 	}
// }

// Clase de datos iniciales, comentada mientras la bd esté en validate
