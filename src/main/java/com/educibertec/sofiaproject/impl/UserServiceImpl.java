package com.educibertec.sofiaproject.impl;

import com.educibertec.sofiaproject.entity.Users;
import com.educibertec.sofiaproject.repositories.IUsuariosRepository;
import com.educibertec.sofiaproject.security.AuthProvider;
import com.educibertec.sofiaproject.services.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUsuariosRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthProvider authProvider;

    @Override
    public Optional<Users> create(Users user) {
        return Optional.of(repository.save(user));
    }

    @Override
    public Optional<Users> update(Users user) {
        Optional<Users> u = repository.findById(user.getIdusuario());
        if (u.isPresent()) {
            return Optional.of(repository.save(user));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Users> findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public Optional<Users> signIn(Users user) {
        return Optional.ofNullable(repository.findByUsername(user.getUsername())
                .map(u -> {
                    boolean isValid = passwordEncoder.matches(user.getPassword(), u.getPassword());
                    if (isValid) {
                        final String token = authProvider.createToken(u);
                        log.info(String.format("Token: %s", token));
                        u.setPassword("");
                        u.setToken(token);
                        return u;
                    }
                    return null;
                }).orElseGet(() -> null));
    }

    @Override
    public Users findUserById(Long id) {
        return repository.findById(id)
                .orElseGet(() -> null);
    }

    @Override
    public List<Users> findAll() {
        return repository.findAll();
    }
}
