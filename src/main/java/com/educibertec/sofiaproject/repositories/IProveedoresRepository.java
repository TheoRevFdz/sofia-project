package com.educibertec.sofiaproject.repositories;

import com.educibertec.sofiaproject.entity.CapsulaProveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProveedoresRepository extends JpaRepository<CapsulaProveedor, Long> {
    List<CapsulaProveedor> findByEstado(int estado);
}
