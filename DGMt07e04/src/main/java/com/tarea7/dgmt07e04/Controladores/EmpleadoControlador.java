package com.tarea7.dgmt07e04.Controladores;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.tarea7.dgmt07e04.Domain.Empleado;
import com.tarea7.dgmt07e04.Domain.sortDTO;
import com.tarea7.dgmt07e04.Servicios.dService;
import com.tarea7.dgmt07e04.Servicios.eService;

import jakarta.validation.Valid;

@Controller
public class EmpleadoControlador {

    @Autowired
    private eService eServicio;
    @Autowired
    private dService dServicio;
    private String errmsg = null;

    @GetMapping("/")
    public String getIndex(Model model) {
        model.addAttribute("empleados", eServicio.listAll());
        model.addAttribute("deptos", dServicio.listAll());
        model.addAttribute("sortForm", new sortDTO());
        if (errmsg != null) {
            model.addAttribute("error", errmsg);
            errmsg = null;
        }
        return "empleado/indexView";
    }

    @PostMapping("/sort")
    public String postIndexName(sortDTO sortform, Model model) {
        model.addAttribute("empleados",
                eServicio.listSorted(sortform.getSortgender(), sortform.getSortname(), sortform.getSortdepto()));
        model.addAttribute("deptos", dServicio.listAll());
        model.addAttribute("sortForm", sortform);
        if (errmsg != null) {
            model.addAttribute("error", errmsg);
            errmsg = null;
        }
        return "empleado/indexView";
    }

    // @GetMapping("/sort/genero/{genero}")
    // public String getMethodName(@PathVariable Genero genero, Model model) {
    // model.addAttribute("generoSeleccionado", genero);
    // model.addAttribute("empleados", eServicio.listGender(genero));
    // model.addAttribute("sortForm", new sortDTO());
    // if (errmsg != null) {
    // model.addAttribute("error", errmsg);
    // errmsg = null;
    // }
    // return "indexView";
    // }

    @GetMapping("/info/{id}")
    public String getInfo(@PathVariable long id, Model model) {
        try {
            model.addAttribute("empleado", eServicio.getById(id));
        } catch (Exception e) {
            errmsg = e.getMessage();
            return "redirect:/";
        }
        return "empleado/infoView";
    }

    @GetMapping("/edit/{id}")
    public String getEdit(@PathVariable long id, Model model) {
        try {
            addAtributos(model, eServicio.getById(id));
            if (errmsg != null) {
                addError(model, errmsg);
            }
        } catch (Exception e) {
            errmsg = e.getMessage();
            return "redirect:/";
        }
        return "empleado/editView";
    }

    // Metodo para editar, verifica si el bindingResult tiene errores y los envía a
    // la ventana de vuelta con los datos del usuario
    // el mtodo es similar en editar y agregar, tanto en empleado como departamento.
    @PostMapping("/edit/submit")
    public String postedit(@Valid Empleado empform, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            errmsg = bindingResult.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.joining(" | "));
            return "redirect:/edit/" + empform.getId();
        }
        // Segunda verificación tras el binding result
        try {
            eServicio.modificar(empform);
        } catch (Exception e) {
            errmsg = e.getMessage();
            return "redirect:/edit/" + empform.getId();
        }
        return "redirect:/";
    }

    @GetMapping("/add")
    public String getAdd(Model model) {
        if (errmsg != null) {
            addError(model, errmsg);
        }
        addAtributos(model, new Empleado());
        return "empleado/addView";
    }

    @PostMapping("/add/submit")
    public String postAdd(@Valid Empleado empform, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", bindingResult.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.joining(" | ")));
            addAtributos(model, empform);
            return "empleado/addView";
        }
        try {
            eServicio.agregar(empform);
        } catch (Exception e) {
            addError(model, e.getMessage());
            addAtributos(model, empform);
            return "empleado/addView";
        }
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String getDelete(@PathVariable long id) {
        try {
            eServicio.borrarPorId(id);
        } catch (Exception e) {
            errmsg = e.getMessage();
        }
        return "redirect:/";
    }

    // pequeña reduccion de codigo para agregar atributos
    private void addAtributos(Model model, Empleado empform) {
        model.addAttribute("empForm", empform);
        model.addAttribute("deptos", dServicio.listAll());
    }

    private void addError(Model model, String error) {
        model.addAttribute("error", error);
        errmsg = null;
    }
}
