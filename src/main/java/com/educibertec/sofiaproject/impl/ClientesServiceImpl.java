package com.educibertec.sofiaproject.impl;

import com.educibertec.sofiaproject.entity.CapsulaCliente;
import com.educibertec.sofiaproject.repositories.IClientesRepository;
import com.educibertec.sofiaproject.services.IClientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClientesServiceImpl implements IClientesService {
	@Autowired
	IClientesRepository rc;
	
	@Override
	public List<CapsulaCliente> listar() {
		return rc.findByEstado(1);
	}

	@Override
	public CapsulaCliente buscarCliente(Long codigo) {
		return rc.findById(codigo).orElse(null);
	}

	@Override
	public void eliminarCliente(CapsulaCliente obj) {
		CapsulaCliente cli = rc.findById(obj.getIdcliente()).orElse(null);
		cli.setEstado(0);
		rc.save(cli);
	}

	@Override
	public void modificarCliente(CapsulaCliente obj) {
		CapsulaCliente cli = rc.findById(obj.getIdcliente()).orElse(null);
		cli.setIdcliente(obj.getIdcliente());
		cli.setCelular(obj.getCelular());
		cli.setDireccion(obj.getDireccion());
		cli.setRazonsocial(obj.getRazonsocial());
		cli.setRucdni(obj.getRucdni());
		cli.setEstado(1);
		rc.save(cli);
	}

	@Override
	public void crearCliente(CapsulaCliente obj) {
		rc.save(obj);
	}

}
