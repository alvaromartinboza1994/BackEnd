package com.proyectosPersonales.springboot.webflux.app.handler;

import static org.springframework.web.reactive.function.BodyInserters.*;

import java.io.File;
import java.net.URI;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
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
	
	@Value("${config.uploads.path}")
	private String path;

	/*
	 * public Mono<ServerResponse> upload(ServerRequest request) { String id =
	 * request.pathVariable("id"); return request.multipartData() .map(multipart ->
	 * multipart.toSingleValueMap().get("file")) .cast(FilePart.class)
	 * .flatMap(filepart -> service.findById(id) .flatMap(p -> {
	 * p.setFoto(UUID.randomUUID().toString() + "-" + file.filename() .replace(" ",
	 * "-") .replace(":", "") .replace("\\", "")); return file.transferTo(new
	 * File(path + p.getFoto())).then(service.save(p)); })).flatmap(p ->
	 * ServerResponse.created(URI.create("/api/v2/productos".concat(p.getId())))
	 * .contentType(MediaType.APPLICATION_JSON_UTF8) .body(fromObject(p)))
	 * .switchIfEmpty(ServerResponse.notFound().build()); }
	 */

	public Mono<ServerResponse> listar(ServerRequest request) {
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(service.findAll(), Producto.class);
	}

	public Mono<ServerResponse> ver(ServerRequest request) {
		String id = request.pathVariable("id");
		return service.findById(id)
				.flatMap(p -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(fromObject(p)))// no
																													// es
																													// un
																													// tipo
																													// reactivo,
																													// es
																													// un
																													// objeto
																													// que
																													// se
																													// esta
																													// emitiendo
				.switchIfEmpty(ServerResponse.notFound().build());
	}

	public Mono<ServerResponse> crear(ServerRequest request) {
		Mono<Producto> producto = request.bodyToMono(Producto.class);

		return producto.flatMap(p -> {
			if (p.getCreateAt() == null) {
				p.setCreateAt(new Date());
			}
			return service.save(p);
		}).flatMap(p -> ServerResponse.created(URI.create("/api/v2/productos".concat(p.getId())))
				.contentType(MediaType.APPLICATION_JSON_UTF8).body(fromObject(p)));
	}

	public Mono<ServerResponse> editar(ServerRequest request) {
		Mono<Producto> producto = request.bodyToMono(Producto.class);
		String id = request.pathVariable("id");
		Mono<Producto> productoDb = service.findById(id);

		return productoDb.zipWith(producto, (db, req) -> {
			db.setNombre(req.getNombre());
			db.setPrecio(req.getPrecio());
			db.setCategoria(req.getCategoria());
			return db;
		}).flatMap(p -> ServerResponse.created(URI.create("/api/v2/productos".concat(p.getId())))
				.contentType(MediaType.APPLICATION_JSON_UTF8).body(service.save(p), Producto.class))
				.switchIfEmpty(ServerResponse.notFound().build());
	}

	public Mono<ServerResponse> eliminar(ServerRequest request) {
		String id = request.pathVariable("id");
		Mono<Producto> productoDb = service.findById(id);

		return productoDb.flatMap(p -> service.delete(p).then(ServerResponse.noContent().build()))
				.switchIfEmpty(ServerResponse.notFound().build());
	}
}
