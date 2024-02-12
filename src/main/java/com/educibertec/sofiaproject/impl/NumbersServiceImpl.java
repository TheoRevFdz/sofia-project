package com.educibertec.sofiaproject.impl;

import com.educibertec.sofiaproject.entity.CapsulaNumber;
import com.educibertec.sofiaproject.repositories.INumbersRepository;
import com.educibertec.sofiaproject.services.INumbersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NumbersServiceImpl implements INumbersService {
    @Autowired
    INumbersRepository rn;

    @Override
    public CapsulaNumber buscarNumeracion(Long id) {
        CapsulaNumber num = rn.findById(id).orElse(null);
        int correl = num.getNumeracion() + 1;
        num.setNumeracion(correl);
        rn.save(num);
        return rn.findById(id).orElse(null);
    }
}
