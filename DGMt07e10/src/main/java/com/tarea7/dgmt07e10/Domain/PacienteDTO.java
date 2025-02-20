package com.tarea7.dgmt07e10.Domain;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "dni")
public class PacienteDTO {
    @NotEmpty
    @NotNull
    @Size(min = 5, max = 11)
    private String dni;

    @NotEmpty
    @NotNull
    @Size(min = 3, max = 255)    
    private String nombre;

    @NotNull
    @Past
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;

    private int tipoPaciente;

    private String motivo;

    @Min(value = 1, message = "Debe haber al menos 1 medicamento")
    private Long medicamentos;

    @Past
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate ultimaRevision;
}
