package com.tarea7.dgmt07e10.Domain;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.tarea7.dgmt07e10.Configuracion.Tarifas;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
// @DiscriminatorValue( value="Revision" )
public class PacienteRevision extends Paciente {
    private LocalDate ultimaRevision;

    public double facturar(Tarifas tarifas) {
        long edad = ChronoUnit.YEARS.between(this.getFechaNacimiento(), LocalDate.now());
        if (edad > 65) {
            return tarifas.getTarifaRevisionJubilado();
        } else {
            return tarifas.getTarifaRevisionAdulto();
        }
    }
}
