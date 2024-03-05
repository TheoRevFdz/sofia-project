package com.educibertec.sofiaproject.impl;

import com.educibertec.sofiaproject.entity.Operacion;
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
	public void crearOperaciones(Operacion obj) {
		ro.save(obj);
	}

	@Override
	public void crearMultiplesOperaciones(List<Operacion> obj) {
		ro.saveAll(obj);
	}
}
