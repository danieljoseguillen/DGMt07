package com.tarea7.dgmt07e04.Controladores;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tarea7.dgmt07e04.Domain.Integrante;
import com.tarea7.dgmt07e04.Servicios.iService;
import com.tarea7.dgmt07e04.Servicios.eService;
import com.tarea7.dgmt07e04.Servicios.pService;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/integrantes")
public class IntegranteControlador {

    @Autowired
    private iService servicio;
    @Autowired
    private pService pServ;
    @Autowired
    private eService eServ;

    private String errmsg = null;

// Metodo que servirá para mostrar integranteses del proyecto o empleado.
    @GetMapping("/{tipo}/{id}")
    public String getIndex(@PathVariable String tipo,@PathVariable long id,Model model) {
        model.addAttribute("tipo", tipo);
        if ("proyecto".equals(tipo)) {
            model.addAttribute("integrantes", servicio.getByProyecto(id));
            model.addAttribute("objeto", pServ.getById(id));
        }else if("empleado".equals(tipo)){
            model.addAttribute("integrantes", servicio.getByEmpleado(id));
            model.addAttribute("objeto", eServ.getById(id));
        }
        if (errmsg != null) {
            addError(model, errmsg);
        }
        return "integrantes/indexView";
    }

    @GetMapping("/add")
    public String getAdd(Model model) {
        if (errmsg != null) {
            addError(model, errmsg);
        }
        addAtributos(model, new Integrante());
        return "integrantes/addView";
    }

    @PostMapping("/add/submit")
    public String postAdd(@Valid Integrante inteform, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", bindingResult.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.joining(" | ")));
            addAtributos(model, inteform);
            return "integrantes/addView";
        }
        try {
            servicio.agregar(inteform);
        } catch (Exception e) {
            addError(model, e.getMessage());
            addAtributos(model, inteform);
            return "integrantes/addView";
        }
        return "redirect:/proyecto/";
    }

    @GetMapping("/delete/{tipo}/{val}/{id}")
    public String getDelete(@PathVariable String tipo,@PathVariable long val,@PathVariable long id) {
        try {
            servicio.borrarPorId(id);
        } catch (Exception e) {
            errmsg = e.getMessage();
        }
        return "redirect:/integrantes/"+tipo+"/"+val;
    }

        private void addAtributos(Model model, Integrante inteform) {
        model.addAttribute("inteForm", inteform);
        model.addAttribute("empleados", eServ.listAll());
        model.addAttribute("proyectos", pServ.listAll());
    }

    private void addError(Model model, String error) {
        model.addAttribute("error", error);
        errmsg = null;
    }
}
