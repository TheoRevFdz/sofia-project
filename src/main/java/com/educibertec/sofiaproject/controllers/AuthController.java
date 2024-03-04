package com.educibertec.sofiaproject.controllers;

import com.educibertec.sofiaproject.entity.CapsulaUsuario;
import com.educibertec.sofiaproject.services.IUserService;
import com.educibertec.sofiaproject.services.IUsuariosService;
import com.educibertec.sofiaproject.util.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/login")
public class AuthController {
    @Autowired
    private IUsuariosService SUsuario;
    @Autowired
    private IUserService userService;
    @Autowired
    private AuthUtil authUtil;
    @Autowired
    private SessionRegistry sessionRegistry;

    @GetMapping
    public ModelAndView auth() {
        ModelAndView mav = new ModelAndView("autentificacion");
        mav.addObject("pqteusers", new CapsulaUsuario());
        return mav;
    }

    @PostMapping
    public String login(@RequestBody CapsulaUsuario user) {
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

    @GetMapping("/session")
    public ResponseEntity<?> getDetailsSession() {
        String sessionId = "";
        User user = null;
        List<Object> sessions = sessionRegistry.getAllPrincipals();
        for (Object session : sessions) {
            if (session instanceof User) {
                user = (User) session;
            }
            List<SessionInformation> sessionInformations = sessionRegistry.getAllSessions(session, false);
            for (SessionInformation sessionInformation : sessionInformations) {
                sessionId = sessionInformation.getSessionId();
            }
        }
        Map<String, Object> response = new HashMap<>();
        response.put("response", "Hello world");
        response.put("sessionId", sessionId);
        response.put("sessionUser", user);
        return ResponseEntity.ok(response);
    }
}
