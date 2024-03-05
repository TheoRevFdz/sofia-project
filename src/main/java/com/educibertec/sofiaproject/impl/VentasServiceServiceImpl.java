package com.educibertec.sofiaproject.impl;

import com.educibertec.sofiaproject.entity.Sale;
import com.educibertec.sofiaproject.repositories.IVentasRepository;
import com.educibertec.sofiaproject.services.IVentasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class VentasServiceServiceImpl implements IVentasService {
	@Autowired
	IVentasRepository rv;
	
	@Override
	public void crearVentas(Sale obj) {
		rv.save(obj);
	}

}
