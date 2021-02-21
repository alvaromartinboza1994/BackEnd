package com.proyectosPersonales.springboot.webflux.app.models.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.proyectosPersonales.springboot.webflux.app.models.documents.Producto;

public interface ProductoDao extends ReactiveMongoRepository<Producto, String>{
//cualquier repositorio de spring es componente por defecto y se puede inyectar
	
}
