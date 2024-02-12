package com.educibertec.sofiaproject.services;

import com.educibertec.sofiaproject.entity.CapsulaOperacion;

import java.util.List;

public interface IOperacionesService {

	public void crearOperaciones(CapsulaOperacion obj);
	public void crearMultiplesOperaciones(List<CapsulaOperacion> obj);

}
