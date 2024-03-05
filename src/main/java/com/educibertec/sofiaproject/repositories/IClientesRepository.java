package com.educibertec.sofiaproject.repositories;

import com.educibertec.sofiaproject.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IClientesRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByEstado(int estado);
}
