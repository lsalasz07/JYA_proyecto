package com.JYA_proyecto.JYA_proyecto.controller;

import com.JYA_proyecto.JYA_proyecto.model.MensajeContacto;
import com.JYA_proyecto.JYA_proyecto.dao.MensajeContactoDao;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@Controller
public class ContactoController {

    @Autowired
    private MensajeContactoDao dao;

@GetMapping("/contacto")
public String verFormulario(Model model) {
    if (!model.containsAttribute("form")) {
        model.addAttribute("form", new MensajeContacto());
    }
    return "contacto";
}

@PostMapping("/contacto")
public String enviar(@Valid @ModelAttribute("form") MensajeContacto form,
                     BindingResult br,
                     RedirectAttributes ra) {

    if (br.hasErrors()) {
        ra.addFlashAttribute("org.springframework.validation.BindingResult.form", br);
        ra.addFlashAttribute("form", form);
        return "redirect:/contacto";
    }

    form.setFechaEnvio(LocalDateTime.now());
    dao.save(form);

    ra.addFlashAttribute("ok", true);
    return "redirect:/contacto";
}

}
