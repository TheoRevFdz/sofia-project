package com.educibertec.sofiaproject.services;

import com.educibertec.sofiaproject.entity.Proveedor;

import java.util.List;

public interface IProveedoresService {

	public List<Proveedor> listar();
	public Proveedor buscarProveedor(Long codigo);
	public void eliminarProveedor(Proveedor obj);
	public void modificarProveedor(Proveedor obj);
	public void crearProveedor(Proveedor obj);
	
}
