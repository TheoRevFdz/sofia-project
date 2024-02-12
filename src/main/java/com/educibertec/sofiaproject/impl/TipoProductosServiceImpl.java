package com.educibertec.sofiaproject.impl;

import com.educibertec.sofiaproject.entity.CapsulaTipoProducto;
import com.educibertec.sofiaproject.repositories.ITipoProductosRepository;
import com.educibertec.sofiaproject.services.ITipoProductosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoProductosServiceImpl implements ITipoProductosService {
	@Autowired
	ITipoProductosRepository rt;
	
	@Override
	public List<CapsulaTipoProducto> listar() {
		return rt.findAll();
	}

}
