package com.tarea7.dgmt07e02b.Domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
public class Departamento {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, message = "El nombre debe tener al menos 3 caracteres.")
    @NotEmpty(message = "El nombre no puede estar vacío.")
    @NotNull(message = "El nombre es obligatorio.")
    @Column(unique=true)
    private String nombre;

    @Min(value = 20000, message = "El presupuesto no puede ser menor a 20,000.")
    private Double presupuesto;

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "departamento")
    private List<Empleado> empleados= new ArrayList<>();
}
