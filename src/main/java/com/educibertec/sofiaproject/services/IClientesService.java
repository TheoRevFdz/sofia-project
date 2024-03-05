package com.educibertec.sofiaproject.services;

import com.educibertec.sofiaproject.entity.Customer;

import java.util.List;

public interface IClientesService {
	
	public List<Customer> listar();
	public Customer buscarCliente(Long codigo);
	public void eliminarCliente(Customer obj);
	public void modificarCliente(Customer obj);
	public void crearCliente(Customer obj);
	
	
}
