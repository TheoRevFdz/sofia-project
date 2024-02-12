package com.educibertec.sofiaproject.services;

import com.educibertec.sofiaproject.entity.CapsulaProveedor;

import java.util.List;

public interface IProveedoresService {

	public List<CapsulaProveedor> listar();
	public CapsulaProveedor buscarProveedor(Long codigo);
	public void eliminarProveedor(CapsulaProveedor obj);
	public void modificarProveedor(CapsulaProveedor obj);
	public void crearProveedor(CapsulaProveedor obj);
	
}
