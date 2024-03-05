package com.educibertec.sofiaproject.controllers;

import com.educibertec.sofiaproject.entity.Users;
import com.educibertec.sofiaproject.services.IUserService;
import com.educibertec.sofiaproject.services.IUsuariosService;
import com.educibertec.sofiaproject.util.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login")
public class AuthController {
    @Autowired
    private IUsuariosService SUsuario;
    @Autowired
    private IUserService userService;
    @Autowired
    private AuthUtil authUtil;

    @GetMapping
    public ModelAndView auth() {
        ModelAndView mav = new ModelAndView("autentificacion");
        mav.addObject("pqteusers", new Users());
        return mav;
    }

    @PostMapping
    public String login(@RequestBody Users user) {
        final String response = userService.signIn(user)
                .map(u -> {
                    if (u.getEstado() == 1) {
                        return "redirect:/login";
                    }
                    return "";
                })
                .orElseGet(() -> "");
        if (response.isEmpty()) {
            //attribute.addFlashAttribute("warning", "Datos de usuario incorrectos.");
            return "redirect:/login";
        }
        return "redirect:/listar_Ctrl_Reposicion";
    }
}
