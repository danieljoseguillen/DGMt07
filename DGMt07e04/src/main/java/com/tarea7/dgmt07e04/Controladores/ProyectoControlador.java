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

import com.tarea7.dgmt07e04.Domain.Proyecto;
import com.tarea7.dgmt07e04.Servicios.pService;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/proyecto")
public class ProyectoControlador {

    @Autowired
    private pService servicio;

    private String errmsg = null;

    @GetMapping("/")
    public String getIndex(Model model) {
        model.addAttribute("proyectos", servicio.listAll());
        if (errmsg != null) {
            addError(model, errmsg);
        }
        return "proyecto/indexView";
    }

    @GetMapping("/info/{id}")
    public String getInfo(@PathVariable long id, Model model) {
        try {
            model.addAttribute("proyecto", servicio.getById(id));
        } catch (Exception e) {
            errmsg = e.getMessage();
            return "redirect:/proyecto/";
        }
        return "proyecto/infoView";
    }

    @GetMapping("/edit/{id}")
    public String getEdit(@PathVariable long id, Model model) {
        try {
            model.addAttribute("proyForm", servicio.getById(id));
            if (errmsg != null) {
                addError(model, errmsg);
            }
        } catch (Exception e) {
            errmsg = e.getMessage();
            return "redirect:/proyecto/";
        }
        return "proyecto/editView";
    }

    @PostMapping("/edit/submit")
    public String postedit(@Valid Proyecto proyform, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            errmsg = bindingResult.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.joining(" | "));
            return "redirect:/proyecto/edit/" + proyform.getId();
        }
        try {
            servicio.modificar(proyform);
        } catch (Exception e) {
            errmsg = e.getMessage();
            return "redirect:/proyecto/edit/" + proyform.getId();
        }
        return "redirect:/proyecto/";
    }

    @GetMapping("/add")
    public String getAdd(Model model) {
        if (errmsg != null) {
            addError(model, errmsg);
        }
        model.addAttribute("proyForm", new Proyecto());
        return "proyecto/addView";
    }

    @PostMapping("/add/submit")
    public String postAdd(@Valid Proyecto proyform, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", bindingResult.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.joining(" | ")));
            model.addAttribute("proyForm", proyform);
            return "proyecto/addView";
        }
        try {
            servicio.agregar(proyform);
        } catch (Exception e) {
            addError(model, e.getMessage());
            model.addAttribute("proyForm", proyform);
            return "proyecto/addView";
        }
        return "redirect:/proyecto/";
    }

    @GetMapping("/delete/{id}")
    public String getDelete(@PathVariable long id) {
        try {
            servicio.borrarPorId(id);
        } catch (Exception e) {
            errmsg = e.getMessage();
        }
        return "redirect:/proyecto/";
    }

    private void addError(Model model, String error) {
        model.addAttribute("error", error);
        errmsg = null;
    }
}
