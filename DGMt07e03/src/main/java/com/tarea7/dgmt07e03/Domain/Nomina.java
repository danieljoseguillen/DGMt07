package com.tarea7.dgmt07e03.Domain;

import java.time.LocalDate;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
public class Nomina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate fechaNomina;

    @Min(value = 0, message = "El Importe bruto debe ser positivo")
    @NotNull(message = "El importe bruto no puede estar vacío.")
    Double importeBruto;

    @Max(value = 100, message = "El porcetaje de impuesto no puede pasar del 100%")
    @NotNull(message = "El porcentaje no puede estar vacío.")
    Double porcentImpuestos;

    @Min(value = 0, message = "El Importe neto debe ser positivo")
    @NotNull(message = "El importe neto no puede estar vacío.")
    Double importeNeto;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Empleado empleado;

}