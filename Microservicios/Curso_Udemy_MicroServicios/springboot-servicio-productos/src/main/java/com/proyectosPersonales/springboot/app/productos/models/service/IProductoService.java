package com.proyectosPersonales.springboot.app.productos.models.service;

import java.util.List;

import com.proyectosPersonales.springboot.app.productos.models.entity.Producto;

public interface IProductoService {
	
	public List<Producto> findAll();
	public Producto findById(Long id);
	public Producto save(Producto producto);//CRUD
	public void deleteById(Long id);//CRUD

}
