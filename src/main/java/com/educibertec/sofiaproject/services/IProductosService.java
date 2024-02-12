package com.educibertec.sofiaproject.services;

import com.educibertec.sofiaproject.entity.CapsulaProducto;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

import java.io.FileNotFoundException;
import java.util.List;

public interface IProductosService {

	public List<CapsulaProducto> listar();
	public CapsulaProducto buscarProducto(Long codigo);
	public void eliminarProducto(CapsulaProducto obj);
	public void modificarProducto(CapsulaProducto obj);
	public void crearProducto(CapsulaProducto obj);
	public JasperPrint exportReport(String report)throws FileNotFoundException, JRException;
}
