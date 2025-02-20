package com.tarea7.dgmt07e10.Domain;

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
// @DiscriminatorValue( value="Consulta" )
public class PacienteConsulta extends Paciente {
    private String motivo;

    public double facturar(Tarifas tarifas) {
        return tarifas.getTarifaConsulta();
    }
}
