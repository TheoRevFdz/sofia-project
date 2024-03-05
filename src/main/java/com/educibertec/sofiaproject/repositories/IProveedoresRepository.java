package com.educibertec.sofiaproject.repositories;

import com.educibertec.sofiaproject.entity.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProveedoresRepository extends JpaRepository<Proveedor, Long> {
    List<Proveedor> findByEstado(int estado);
}
