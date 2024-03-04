package com.educibertec.sofiaproject.controllers;

import com.educibertec.sofiaproject.entity.CapsulaUsuario;
import com.educibertec.sofiaproject.services.IUserService;
import com.educibertec.sofiaproject.services.IUsuariosService;
import com.educibertec.sofiaproject.util.AuthUtil;
import com.educibertec.sofiaproject.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    @Autowired
    private IUserService service;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private AuthUtil authUtil;
    @Autowired
    private IUsuariosService usuariosService;

    @PutMapping
    public ResponseEntity<MessageUtil> update(@RequestBody CapsulaUsuario user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return authUtil.responseEntityMono(service.update(user),
                "Usuario actualizado correctamente",
                "No se encontró el usuario que intenta actualizar.");
    }

    @GetMapping
    public ResponseEntity<CapsulaUsuario> findByUsername(@RequestParam(name = "username") String username) {
        return service.findByUsername(username)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/all")
    public ResponseEntity<List<CapsulaUsuario>> findAll() {
        List<CapsulaUsuario> resp = service.findAll().stream()
                .peek(u -> u.setPassword("")).collect(Collectors.toList());
        return ResponseEntity.ok(resp);
    }

    @PostMapping("/createUser")
    public ResponseEntity<Object> createUser(@RequestBody CapsulaUsuario usuario) {
        Optional<CapsulaUsuario> respUser = null;
        try {
            respUser = Optional.of(usuariosService.signUp(usuario));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        MessageUtil resp = MessageUtil.builder()
                .timestamp(new Date())
                .build();
        if (respUser.isPresent()) {
            resp.setMessage("Usuario creado correctamente.");
            CapsulaUsuario user = respUser.get();
            user.setPassword("");
            resp.setResult(user);
            resp.setStatus(HttpStatus.CREATED.value());
            return ResponseEntity.status(HttpStatus.CREATED).body(resp);
        } else {
            resp.setStatus(HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(resp);
        }
    }
}
