package com.educibertec.sofiaproject.controllers;

import com.educibertec.sofiaproject.entity.CapsulaUsuario;
import com.educibertec.sofiaproject.services.IUserService;
import com.educibertec.sofiaproject.services.IUsuariosService;
import com.educibertec.sofiaproject.util.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private IUsuariosService SUsuario;
    @Autowired
    private IUserService userService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private AuthUtil authUtil;

    @GetMapping
    public ModelAndView auth() {
        ModelAndView mav = new ModelAndView("autentificacion");
        mav.addObject("pqteusers", new CapsulaUsuario());
        return mav;
    }

    @PostMapping
    public String login(@ModelAttribute(name = "pqteusers") CapsulaUsuario obj, BindingResult result, RedirectAttributes attribute) {
        final String response = userService.signIn(obj)
                .map(u -> {
                    if (u.getEstado() == 1) {
                        return "redirect:/auth";
                    }
                    return "";
                })
                .orElseGet(() -> "");
        if (response.isEmpty()) {
            attribute.addFlashAttribute("warning", "Datos de usuario incorrectos.");
            return "redirect:/auth";
        }
        return "redirect:/listar_Ctrl_Reposicion";
    }
}
