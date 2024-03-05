package com.educibertec.sofiaproject.repositories;

import com.educibertec.sofiaproject.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUsuariosRepository extends JpaRepository<Users, Long> {
    List<Users> findByUsernameAndPassword(String usuario, String clave);

    Optional<Users> findByUsername(String username);
}
