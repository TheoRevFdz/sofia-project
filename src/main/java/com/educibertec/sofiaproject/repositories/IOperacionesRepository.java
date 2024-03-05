package com.educibertec.sofiaproject.repositories;

import com.educibertec.sofiaproject.entity.Operacion;
import com.educibertec.sofiaproject.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOperacionesRepository extends JpaRepository<Operacion, Long> {
    List<Operacion> findByProducto(Product producto);
}
