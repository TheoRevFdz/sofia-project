package com.educibertec.sofiaproject.repositories;

import com.educibertec.sofiaproject.entity.CapsulaProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductosRepository extends JpaRepository<CapsulaProducto, Long> {
    List<CapsulaProducto> findByEstado(int estado);
}
