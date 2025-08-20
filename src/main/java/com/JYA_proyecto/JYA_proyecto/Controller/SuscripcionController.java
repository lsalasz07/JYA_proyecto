package com.JYA_proyecto.JYA_proyecto.controller;

import com.JYA_proyecto.JYA_proyecto.dao.SuscripcionDao;
import com.JYA_proyecto.JYA_proyecto.model.Suscripcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.regex.Pattern;

@Controller
public class SuscripcionController {

    @Autowired
    private SuscripcionDao dao;

    // Regex simple para validar email
    private static final Pattern EMAIL_RX =
            Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    @PostMapping("/suscribirse")
    public String suscribirse(@RequestParam("email") String email,
                              RedirectAttributes ra,
                              HttpServletRequest request) {
        String ref = request.getHeader("Referer");
        String redirect = (ref != null && !ref.isBlank()) ? "redirect:" + ref : "redirect:/";

        if (email == null || email.isBlank() || !EMAIL_RX.matcher(email.trim()).matches()) {
            ra.addFlashAttribute("sub_err", "Ingresa un correo válido.");
            return redirect;
        }

        String normalized = email.trim().toLowerCase();

        if (dao.existsByEmail(normalized)) {
            ra.addFlashAttribute("sub_info", normalized);
            return redirect;
        }

        Suscripcion s = new Suscripcion();
        s.setEmail(normalized);
        s.setFechaAlta(LocalDateTime.now());
        dao.save(s);

        ra.addFlashAttribute("sub_ok", normalized);
        return redirect;
    }
}
