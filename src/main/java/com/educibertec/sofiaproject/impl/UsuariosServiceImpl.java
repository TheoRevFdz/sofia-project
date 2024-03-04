package com.educibertec.sofiaproject.impl;

import com.educibertec.sofiaproject.entity.CapsulaUsuario;
import com.educibertec.sofiaproject.exceptions.NoDataFoundException;
import com.educibertec.sofiaproject.exceptions.ValidateServiceException;
import com.educibertec.sofiaproject.repositories.IUsuariosRepository;
import com.educibertec.sofiaproject.services.IUsuariosService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Log4j2
@Service
public class UsuariosServiceImpl implements IUsuariosService {
    @Value("${config.security.keySecret}")
    private String jwtSecret;
    @Autowired
    private IUsuariosRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public CapsulaUsuario validarLogin(String username, String password) {
        CapsulaUsuario user = repository.findByUsername(username)
                .orElseThrow(() -> new ValidateServiceException("Usuario o password incorrectos"));
        if (passwordEncoder.matches(password, user.getPassword())) {
            final String token = generateToken(user);
            user.setToken(token);
            return user;
        }
        throw new ValidateServiceException("Usuario o password incorrectos");
    }

    public String generateToken(CapsulaUsuario user) {
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + (1000 * 60 * 60 * 24 * 7));

        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    @Override
    public CapsulaUsuario signUp(CapsulaUsuario user) throws Exception {
        try {
            CapsulaUsuario userResult = repository.findByUsername(user.getUsername()).orElse(null);
            if (userResult != null && user.getIdusuario() == null) {
                throw new ValidateServiceException("El nombre de usuario ya existe");
            }

            String encodedPass = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPass);
            return repository.save(user);
        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }
}
