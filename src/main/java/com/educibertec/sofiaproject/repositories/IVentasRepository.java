package com.educibertec.sofiaproject.repositories;

import com.educibertec.sofiaproject.entity.CapsulaVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface IVentasRepository extends JpaRepository<CapsulaVenta, String>{

}
