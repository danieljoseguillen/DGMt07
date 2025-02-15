package com.tarea7.dgmt07e08.Servicios;

import java.util.List;

import com.tarea7.dgmt07e08.Domain.Cuenta;
import com.tarea7.dgmt07e08.Domain.Movimiento;

public interface Servicio {

    List<Cuenta> listAllCuentas();

    Cuenta getById(String IBAN);

    Cuenta agregarCuenta(Cuenta cuenta);

    Cuenta modificarCuenta(Cuenta cuenta);

    boolean borrarCuenta(String IBAN);

    List<Movimiento> listAllAcountMovements(String IBAN);

    Movimiento agregarMovimiento(Movimiento mov);

    String generadorIBAN();
}
