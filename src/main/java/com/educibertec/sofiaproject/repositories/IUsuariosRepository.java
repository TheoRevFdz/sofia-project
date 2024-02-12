package com.educibertec.sofiaproject.repositories;

import com.educibertec.sofiaproject.entity.CapsulaUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUsuariosRepository extends JpaRepository<CapsulaUsuario, Long> {
    List<CapsulaUsuario> findByUsuarioAndClave(String usuario, String clave);

    Optional<CapsulaUsuario> findByUsuario(String username);
}
