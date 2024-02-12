package com.educibertec.sofiaproject.services;

import com.educibertec.sofiaproject.entity.CapsulaCliente;

import java.util.List;

public interface IClientesService {
	
	public List<CapsulaCliente> listar();
	public CapsulaCliente buscarCliente(Long codigo);
	public void eliminarCliente(CapsulaCliente obj);
	public void modificarCliente(CapsulaCliente obj);
	public void crearCliente(CapsulaCliente obj);
	
	
}
