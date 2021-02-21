package com.proyectosPersonales.springboot.webflux.app.controllers;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;

import com.proyectosPersonales.springboot.webflux.app.models.documents.Producto;
import com.proyectosPersonales.springboot.webflux.app.models.services.ProductoService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Controller
@Slf4j
public class ProductoController {

	@Autowired
	private ProductoService service;

	@GetMapping({ "/listar", "/" })
	public String listar(Model model) {
		Flux<Producto> productos = service.findAllConNombreUpperCase();
		productos.subscribe(producto -> log.info(producto.getNombre()));

		model.addAttribute("productos", productos);
		model.addAttribute("titulo", "Listado de productos");

		return "listar";
	}

	@GetMapping("/listar-datadriver")
	public String listarDataDriver(Model model) {
		Flux<Producto> productos = service.findAllConNombreUpperCase().delayElements(Duration.ofSeconds(1));

		productos.subscribe(producto -> log.info(producto.getNombre()));

		model.addAttribute("productos", new ReactiveDataDriverContextVariable(productos, 1)); //buffer se mide en cantidad de elementos
		model.addAttribute("titulo", "Listado de productos");

		return "listar";
	}
	
	@GetMapping({ "/listar-full"})
	public String listarFull(Model model) {
		Flux<Producto> productos = service.findAllConNombreUpperCaseYConRepeat();//repetimos 5000 veces el flujo actual
		
		model.addAttribute("productos", productos);
		model.addAttribute("titulo", "Listado de productos");

		return "listar";
	}
	
	@GetMapping({ "/listar-chunked"})
	public String listarChunked(Model model) {
		Flux<Producto> productos = service.findAllConNombreUpperCaseYConRepeat();//repetimos 5000 veces el flujo actual
		
		model.addAttribute("productos", productos);
		model.addAttribute("titulo", "Listado de productos");

		return "listar-chunked";
	}
}
