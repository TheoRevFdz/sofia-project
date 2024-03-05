package com.educibertec.sofiaproject.repositories;

import com.educibertec.sofiaproject.entity.TipoProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITipoProductosRepository extends JpaRepository<TipoProducto, Long> {

}
