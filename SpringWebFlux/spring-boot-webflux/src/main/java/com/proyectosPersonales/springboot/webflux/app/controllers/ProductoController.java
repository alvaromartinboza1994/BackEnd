package com.proyectosPersonales.springboot.webflux.app.controllers;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;

import com.proyectosPersonales.springboot.webflux.app.models.documents.Producto;
import com.proyectosPersonales.springboot.webflux.app.models.services.ProductoService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SessionAttributes("producto") // cada vez que se pase en el crear o en el editar se pase a la vista se guarda
								// en la sesion http
@Controller
@Slf4j
public class ProductoController {// NO USAMOS EL SUSCRIPTOR EN EL CONTROLADOR

	@Autowired
	private ProductoService service;

	@GetMapping({ "/listar", "/" })
	public Mono<String> listar(Model model) {
		Flux<Producto> productos = service.findAllConNombreUpperCase();
		productos.subscribe(producto -> log.info(producto.getNombre()));

		model.addAttribute("productos", productos);
		model.addAttribute("titulo", "Listado de productos");

		return Mono.just("listar");
	}

	@GetMapping("/form/{id}")
	public Mono<String> editar(@PathVariable String id, Model model) {// model lo usamos para pasar el objeto a la vista
		Mono<Producto> productoMono = service.findById(id).doOnNext(p -> log.info("Producto: " + p.getNombre()))
				.defaultIfEmpty(new Producto());
		model.addAttribute("boton", "Editar");
		model.addAttribute("titulo", "Editar Producto");
		model.addAttribute("producto", productoMono);

		return Mono.just("form");
	}

	@GetMapping("/form-v2/{id}")
	public Mono<String> editarV2(@PathVariable String id, Model model) {// model lo usamos para pasar el objeto a la //
																		// vista
		return service.findById(id).doOnNext(p -> {
			log.info("Producto: " + p.getNombre());
			model.addAttribute("boton", "Editar");
			model.addAttribute("titulo", "Editar Producto");
			model.addAttribute("producto", p);
		}).defaultIfEmpty(new Producto())
				.flatMap(p -> {
					if(p.getId() == null) {
						return Mono.error(new InterruptedException("No existe el producto"));
					}
					return Mono.just(p);
				})
				.then(Mono.just("form"))
				.onErrorResume(ex -> Mono.just("redirect:/listar?error=no+existe+el+producto"));
	}

	@GetMapping("/form")
	public Mono<String> crear(Model model) {// para rellenar mono producto en formulario
		model.addAttribute("producto", new Producto());
		model.addAttribute("titulo", "Formulario de producto");
		model.addAttribute("boton", "Crear");
		return Mono.just("form");
	}

	@PostMapping("/form")
	public Mono<String> guardar(Producto producto, SessionStatus status) {
		status.setComplete();
		return service.save(producto)
				.doOnNext(p -> log.info("producto almacenado: " + p.getNombre() + " Id: " + p.getId()))
				.thenReturn("redirect:/listar");
	}

	@GetMapping("/listar-datadriver")
	public String listarDataDriver(Model model) {
		Flux<Producto> productos = service.findAllConNombreUpperCase().delayElements(Duration.ofSeconds(1));

		productos.subscribe(producto -> log.info(producto.getNombre()));

		model.addAttribute("productos", new ReactiveDataDriverContextVariable(productos, 1)); // buffer se mide en
																								// cantidad de elementos
		model.addAttribute("titulo", "Listado de productos");

		return "listar";
	}

	@GetMapping({ "/listar-full" })
	public String listarFull(Model model) {
		Flux<Producto> productos = service.findAllConNombreUpperCaseYConRepeat();// repetimos 5000 veces el flujo actual

		model.addAttribute("productos", productos);
		model.addAttribute("titulo", "Listado de productos");

		return "listar";
	}

	@GetMapping({ "/listar-chunked" })
	public String listarChunked(Model model) {
		Flux<Producto> productos = service.findAllConNombreUpperCaseYConRepeat();// repetimos 5000 veces el flujo actual

		model.addAttribute("productos", productos);
		model.addAttribute("titulo", "Listado de productos");

		return "listar-chunked";
	}
}
