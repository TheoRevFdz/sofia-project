package com.educibertec.sofiaproject.repositories;

import com.educibertec.sofiaproject.entity.CapsulaCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IClientesRepository extends JpaRepository<CapsulaCliente, Long> {
    List<CapsulaCliente> findByEstado(int estado);
}
