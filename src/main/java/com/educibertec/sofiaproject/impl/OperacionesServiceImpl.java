package com.educibertec.sofiaproject.impl;

import com.educibertec.sofiaproject.entity.CapsulaOperacion;
import com.educibertec.sofiaproject.repositories.IOperacionesRepository;
import com.educibertec.sofiaproject.services.IOperacionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperacionesServiceImpl implements IOperacionesService {
	@Autowired
	IOperacionesRepository ro;
	
	@Override
	public void crearOperaciones(CapsulaOperacion obj) {
		ro.save(obj);
	}

	@Override
	public void crearMultiplesOperaciones(List<CapsulaOperacion> obj) {
		ro.saveAll(obj);
	}
}
