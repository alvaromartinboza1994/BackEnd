package com.proyectosPersonales.springboot.webflux.app.handler;

import static org.springframework.web.reactive.function.BodyInserters.*;

import java.net.URI;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.proyectosPersonales.springboot.webflux.app.models.documents.Producto;
import com.proyectosPersonales.springboot.webflux.app.models.service.ProductoService;

import reactor.core.publisher.Mono;

@Component
public class ProductoHandler {
	
	@Autowired
	private ProductoService service;

	public Mono<ServerResponse> listar(ServerRequest request) {
		return ServerResponse
				.ok()
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(service.findAll(), Producto.class);
	}
	
	public Mono<ServerResponse> ver(ServerRequest request) {
		String id = request.pathVariable("id");
		return service.findById(id)
				.flatMap(p -> ServerResponse
						.ok()
						.contentType(MediaType.APPLICATION_JSON_UTF8)
						.body(fromObject(p)))//no es un tipo reactivo, es un objeto que se esta emitiendo
						.switchIfEmpty(ServerResponse.notFound().build());
	}
	
	public Mono<ServerResponse> crear(ServerRequest request) {
		Mono<Producto> producto = request.bodyToMono(Producto.class);
		
		return producto.flatMap(p -> {
			if(p.getCreateAt() == null) {
				p.setCreateAt(new Date());
			}
			return service.save(p);
		}).flatMap(p -> ServerResponse.created(URI.create("/api/v2/productos".concat(p.getId())))
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(fromObject(p)));
	}
}
