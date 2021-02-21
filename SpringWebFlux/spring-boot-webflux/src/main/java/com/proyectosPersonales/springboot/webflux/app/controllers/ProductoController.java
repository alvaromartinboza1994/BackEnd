package com.proyectosPersonales.springboot.webflux.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.proyectosPersonales.springboot.webflux.app.models.dao.ProductoDao;
import com.proyectosPersonales.springboot.webflux.app.models.documents.Producto;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Controller
@Slf4j
public class ProductoController {

	@Autowired
	private ProductoDao dao;
	
	@GetMapping({"/listar", "/"})
	public String listar(Model model) {
		Flux<Producto> productos = dao.findAll()
				.map(producto -> {
					producto.setNombre(producto.getNombre().toUpperCase());
					return producto;
				});
		productos.subscribe(producto -> log.info(producto.getNombre()));
		
		model.addAttribute("productos", productos);
		model.addAttribute("titulo", "Listado de productos");
		
		return "listar";
	}
}
