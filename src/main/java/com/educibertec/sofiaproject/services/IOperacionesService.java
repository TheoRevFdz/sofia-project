package com.educibertec.sofiaproject.services;

import com.educibertec.sofiaproject.entity.Operacion;

import java.util.List;

public interface IOperacionesService {

	public void crearOperaciones(Operacion obj);
	public void crearMultiplesOperaciones(List<Operacion> obj);

}
