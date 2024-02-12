package com.educibertec.sofiaproject.repositories;

import com.educibertec.sofiaproject.entity.CapsulaNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface INumbersRepository extends JpaRepository<CapsulaNumber, Long> {

}
