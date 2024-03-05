package com.educibertec.sofiaproject.services;

import com.educibertec.sofiaproject.entity.Product;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

import java.io.FileNotFoundException;
import java.util.List;

public interface IProductosService {

	public List<Product> listar();
	public Product buscarProducto(Long codigo);
	public void eliminarProducto(Product obj);
	public void modificarProducto(Product obj);
	public void crearProducto(Product obj);
	public JasperPrint exportReport(String report)throws FileNotFoundException, JRException;
}
