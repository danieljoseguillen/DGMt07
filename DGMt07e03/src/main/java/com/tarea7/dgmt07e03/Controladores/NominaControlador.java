package com.tarea7.dgmt07e03.Controladores;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tarea7.dgmt07e03.Domain.Nomina;
import com.tarea7.dgmt07e03.Servicios.eService;
import com.tarea7.dgmt07e03.Servicios.nService;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/nomina")
public class NominaControlador {

    @Autowired
    private nService servicio;
    @Autowired
    private eService eServicio;
    private String errmsg = null;

    @GetMapping("/")
    public String getIndex(Model model) {
        model.addAttribute("nominas", servicio.listAll());
        if (errmsg != null) {
            addError(model, errmsg);
        }
        return "nomina/indexView";
    }

    @GetMapping("/info/{id}")
    public String getInfo(@PathVariable long id, Model model) {
        try {
            model.addAttribute("nomina", servicio.getById(id));
        } catch (Exception e) {
            errmsg = e.getMessage();
            return "redirect:/nomina/";
        }
        return "nomina/infoView";
    }

    @GetMapping("/edit/{id}")
    public String getEdit(@PathVariable long id, Model model) {
        try {
            model.addAttribute("nomForm", servicio.getById(id));
            model.addAttribute("empleados", eServicio.listAll());
            if (errmsg != null) {
                addError(model, errmsg);
            }
        } catch (Exception e) {
            errmsg = e.getMessage();
            return "redirect:/nomina/";
        }
        return "nomina/editView";
    }

    @PostMapping("/edit/submit")
    public String postedit(@Valid Nomina nomform, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            errmsg = bindingResult.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.joining(" | "));
            return "redirect:/nomina/edit/" + nomform.getId();
        }
        try {
            servicio.modificar(nomform);
        } catch (Exception e) {
            errmsg = e.getMessage();
            return "redirect:/nomina/edit/" + nomform.getId();
        }
        return "redirect:/nomina/";
    }

    @GetMapping("/add")
    public String getAdd(Model model) {
        if (errmsg != null) {
            addError(model, errmsg);
        }
        model.addAttribute("nomForm", new Nomina());
        model.addAttribute("empleados", eServicio.listAll());
        return "nomina/addView";
    }

    @PostMapping("/add/submit")
    public String postAdd(@Valid Nomina nomform, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", bindingResult.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.joining(" | ")));
            model.addAttribute("nomForm", nomform);
            model.addAttribute("empleados", eServicio.listAll());
            return "nomina/addView";
        }
        try {
            servicio.agregar(nomform);
        } catch (Exception e) {
            addError(model, e.getMessage());
            model.addAttribute("nomForm", nomform);
            model.addAttribute("empleados", eServicio.listAll());
            return "nomina/addView";
        }
        return "redirect:/nomina/";
    }

    @GetMapping("/delete/{id}")
    public String getDelete(@PathVariable long id) {
        try {
            servicio.borrarPorId(id);
        } catch (Exception e) {
            errmsg = e.getMessage();
        }
        return "redirect:/nomina/";
    }

    private void addError(Model model, String error) {
        model.addAttribute("error", error);
        errmsg = null;
    }
}
