package com.tarea7.dgmt07e10.Controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.tarea7.dgmt07e10.Domain.Paciente;
import com.tarea7.dgmt07e10.Domain.PacienteDTO;
import com.tarea7.dgmt07e10.Servicios.PatientService;

@Controller
public class Controlador {
    private String errmsg = null;

    @Autowired
    private PatientService servicio;

    @GetMapping("/")
    public String getIndex(Model model) {
        model.addAttribute("listaPacientes", servicio.listAll());
        if (errmsg != null) {
            model.addAttribute("error", errmsg);
            errmsg = null;
        }
        return "indexView";
    }

    @GetMapping("/newPatient")
    public String getNewPatient(Model model) {
        model.addAttribute("newPatient", new PacienteDTO());
        return "newPatientView";
    }

    @PostMapping("/newPatient/submit")
    public String postNewPatient(@Valid @ModelAttribute PacienteDTO newPatient, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errn","Compruebe que los campos tienen los datos correctos");
            model.addAttribute("newPatient", newPatient);
            return "newPatientView";
        }
        try {
            Paciente paciente = servicio.buildFromDTO(newPatient);
            servicio.agregarPaciente(paciente);
        } catch (Exception e) {
            errmsg = e.getMessage();
        }
        return "redirect:/";
    }

    @GetMapping("/nextPatient")
    public String getNexPatient(Model model) {
        try {
            Double importe = servicio.facturar(servicio.getFirst());
            servicio.deleteFirst();
            model.addAttribute("importe", importe);
            return getIndex(model);
            //No pensé que fuera a funcionar, imagine mi cara cuando funcionó. Pero da un pequeño error cuando recargo la pagina.
            //Es decir, según entendí le envié el model al proceso getIndex y ese model ya tiene el atributo de importe...
        } catch (Exception e) {
            errmsg = e.getMessage();
        }
        return "redirect:/";
    }

}
