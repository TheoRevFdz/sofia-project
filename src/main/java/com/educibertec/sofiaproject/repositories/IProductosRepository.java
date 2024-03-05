package com.educibertec.sofiaproject.repositories;

import com.educibertec.sofiaproject.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductosRepository extends JpaRepository<Product, Long> {
    List<Product> findByEstado(int estado);
}
