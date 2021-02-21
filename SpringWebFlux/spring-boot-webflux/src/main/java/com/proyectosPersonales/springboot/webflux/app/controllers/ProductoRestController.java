package com.proyectosPersonales.springboot.webflux.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyectosPersonales.springboot.webflux.app.models.dao.ProductoDao;
import com.proyectosPersonales.springboot.webflux.app.models.documents.Producto;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@RequestMapping("/api/productos")
public class ProductoRestController {

	@Autowired
	private ProductoDao dao;
	
	@GetMapping()//se ejecuta automaticamente al llamar /api/productos
	public Flux<Producto> index() {
		Flux<Producto> productos = dao.findAll().map(producto -> {
			producto.setNombre(producto.getNombre().toUpperCase());
			return producto;
		}).doOnNext((producto -> log.info(producto.getNombre())));
		return productos;
	}
	
	@GetMapping("/{id}")
	public Mono<Producto> show(@PathVariable String id) {
		//Mono<Producto> producto = dao.findById(id);
		Flux<Producto> productos = dao.findAll();
		
		Mono<Producto> producto = productos
				.filter(p -> p.getId().equals(id))
				.next()//retorna el primer item dentro del flux en un nuevo momo
				.doOnNext((p -> log.info(p.getNombre())));
		return producto;
	}
}
