package com.educibertec.sofiaproject.services;

import com.educibertec.sofiaproject.entity.CapsulaUsuario;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    Optional<CapsulaUsuario> create(CapsulaUsuario user);

    Optional<CapsulaUsuario> update(CapsulaUsuario user);

    Optional<CapsulaUsuario> findByUsername(String username);

    Optional<CapsulaUsuario> signIn(CapsulaUsuario user);

    CapsulaUsuario findUserById(Long id);

    List<CapsulaUsuario> findAll();
}
