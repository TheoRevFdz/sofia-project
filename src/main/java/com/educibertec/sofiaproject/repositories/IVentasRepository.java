package com.educibertec.sofiaproject.repositories;

import com.educibertec.sofiaproject.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface IVentasRepository extends JpaRepository<Sale, String>{

}
