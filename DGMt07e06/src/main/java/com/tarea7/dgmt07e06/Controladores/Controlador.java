package com.tarea7.dgmt07e06.Controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tarea7.dgmt07e06.Domain.sortDTO;
import com.tarea7.dgmt07e06.Servicios.eService;

@Controller
public class Controlador {

    @Autowired
    private eService servicio;
    private String errmsg = null;

    @GetMapping("/")
    public String getIndex(@RequestParam(required = false) Integer pag, Model model) {
        int ultPag = servicio.getTotalPaginas() - 1;
        if (pag == null || pag < 0 || pag > ultPag)
            pag = 0;
        Integer pagSig = ultPag > pag ? pag + 1 : ultPag;
        Integer pagAnt = pag > 0 ? pag - 1 : 0;
        model.addAttribute("empleados", servicio.listPaged(pag));
        model.addAttribute("nextPage", pagSig);
        model.addAttribute("prevPage", pagAnt);
        model.addAttribute("lastPage", ultPag);

        model.addAttribute("sortForm", new sortDTO());
        if (errmsg != null) {
            model.addAttribute("error", errmsg);
            errmsg = null;
        }
        return "indexView";
    }

    @PostMapping("/sort")
    public String postIndexName(sortDTO sortform, Model model) {
        model.addAttribute("empleados", servicio.listSorted(sortform.getSortname()));
        model.addAttribute("sortForm", sortform);
        if (errmsg != null) {
            model.addAttribute("error", errmsg);
            errmsg = null;
        }
        return "indexView";
    }

    @GetMapping("/info/{id}")
    public String getInfo(@PathVariable long id, Model model) {
        try {
            model.addAttribute("empleado", servicio.getById(id));
        } catch (Exception e) {
            errmsg = e.getMessage();
            return "redirect:/";
        }
        return "infoView";
    }

}
