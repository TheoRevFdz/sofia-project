package com.educibertec.sofiaproject.services;

import com.educibertec.sofiaproject.entity.Users;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    Optional<Users> create(Users user);

    Optional<Users> update(Users user);

    Optional<Users> findByUsername(String username);

    Optional<Users> signIn(Users user);

    Users findUserById(Long id);

    List<Users> findAll();
}
