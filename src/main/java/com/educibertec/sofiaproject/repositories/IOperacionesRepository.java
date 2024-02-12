package com.educibertec.sofiaproject.repositories;

import com.educibertec.sofiaproject.entity.CapsulaOperacion;
import com.educibertec.sofiaproject.entity.CapsulaProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOperacionesRepository extends JpaRepository<CapsulaOperacion, Long> {
    List<CapsulaOperacion> findByProducto(CapsulaProducto producto);
}
