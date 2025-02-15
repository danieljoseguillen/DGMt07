package com.tarea7.dgmt07e07.Controladores;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tarea7.dgmt07e07.Domain.Departamento;
import com.tarea7.dgmt07e07.Servicios.dService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/depto")
public class DepartamentoControlador {

    @Autowired
    private dService servicio;
    private String errmsg = null;

    @GetMapping("/")
    public String getIndex(Model model) {
        model.addAttribute("departamentos", servicio.listAll());
        if (errmsg != null) {
            model.addAttribute("error", errmsg);
            errmsg = null;
        }
        return "depto/indexView";
    }

    @GetMapping("/info/{id}")
    public String getInfo(@PathVariable long id, Model model) {
        try {
            model.addAttribute("departamento", servicio.getById(id));
        } catch (Exception e) {
            errmsg = e.getMessage();
            return "redirect:/depto/";
        }
        return "depto/infoView";
    }

    @GetMapping("/edit/{id}")
    public String getEdit(@PathVariable long id, Model model) {
        try {
            model.addAttribute("deptForm", servicio.getById(id));
            if (errmsg != null) {
                model.addAttribute("error", errmsg);
                errmsg = null;
            }
        } catch (Exception e) {
            errmsg = e.getMessage();
            return "redirect:/depto";
        }
        return "depto/editView";
    }

    @PostMapping("/edit/submit")
    public String postedit(@Valid Departamento deptform, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            errmsg = bindingResult.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.joining(" | "));
            return "redirect:/depto/edit/" + deptform.getId();
        }
        try {
            servicio.modificar(deptform);
        } catch (Exception e) {
            errmsg = e.getMessage();
            return "redirect:/depto/edit/" + deptform.getId();
        }
        return "redirect:/depto/";
    }

    @GetMapping("/add")
    public String getAdd(Model model) {
        if (errmsg != null) {
            model.addAttribute("error", errmsg);
            errmsg = null;
        }
        model.addAttribute("deptForm", new Departamento());
        return "depto/addView";
    }

    @PostMapping("/add/submit")
    public String postAdd(@Valid Departamento deptform, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", bindingResult.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.joining(" | ")));
            model.addAttribute("deptForm", deptform);
            return "depto/addView";
        }
        try {
            servicio.agregar(deptform);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            errmsg = null;
            model.addAttribute("deptForm", deptform);
            return "depto/addView";
        }
        return "redirect:/depto/";
    }

    @GetMapping("/delete/{id}")
    public String getDelete(@PathVariable long id) {
        try {
            servicio.borrarPorId(id);
        } catch (Exception e) {
            errmsg = e.getMessage();
        }
        return "redirect:/depto/";
    }

}
