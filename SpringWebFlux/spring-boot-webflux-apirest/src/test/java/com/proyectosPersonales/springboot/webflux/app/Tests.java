package com.proyectosPersonales.springboot.webflux.app;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.Collections;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.proyectosPersonales.springboot.webflux.app.models.documents.Categoria;
import com.proyectosPersonales.springboot.webflux.app.models.documents.Producto;
import com.proyectosPersonales.springboot.webflux.app.models.service.ProductoService;

import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //puerto aleatorio disponible
public class Tests {
	
	@Autowired
	private WebTestClient client;
	
	@Autowired
	private ProductoService service;
	
	@Test
	public void listarTest() {
		client.get()//get para listado
		.uri("/api/v2/productos")
		.accept(MediaType.APPLICATION_JSON_UTF8)
		.exchange()//consumir apirest
		.expectStatus().isOk()
		.expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
		.expectBodyList(Producto.class)
		.consumeWith(response -> {
			List<Producto> productos = response.getResponseBody();
			productos.forEach(p -> {
				System.out.println(p.getNombre());
			});
			Assertions.assertThat(productos.size()>0).isTrue();
		});
		
		
		//.hasSize(9);		
	}
	
	
	@Test
	public void ver() {
		Producto producto = service.findByNombre("Sony Camara HD Digital").block();
		client.get()//get para listado
		.uri("/api/v2/productos/{id}", Collections.singletonMap("id", producto.getId()))
		.accept(MediaType.APPLICATION_JSON_UTF8)
		.exchange()//consumir apirest
		.expectStatus().isOk()
		.expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
		.expectBody(Producto.class)
		.consumeWith(response -> {
			Producto p = response.getResponseBody();
			Assertions.assertThat(p.getId()).isNotEmpty();
			Assertions.assertThat(p.getId().length()>0).isTrue();
			Assertions.assertThat(p.getNombre()).isEqualTo("Sony Camara HD Digital");
		});
		/*.expectBody()
		.jsonPath("$.id").isNotEmpty()
		.jsonPath("$.nombre").isEqualTo("Sony Camara HD Digital");*/
	}
	
	@Test
	public void crearTest() {
		Categoria categoria = service.findCategoriaByNombre("Muebles").block();
		Producto producto = Producto.builder().nombre("mesa").precio(100.00).categoria(categoria).build();
		client.post().uri("/api/v2/productos")
		.contentType(MediaType.APPLICATION_JSON_UTF8)//del request con el cual enviamos el json para crear el flujo
		.accept(MediaType.APPLICATION_JSON_UTF8)//lo que esperamos, response
		.body(Mono.just(producto), Producto.class)
		.exchange()
		.expectStatus().isCreated()
		.expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
		.expectBody()
		.jsonPath("$.id").isNotEmpty()
		.jsonPath("$.nombre").isEqualTo("mesa")
		.jsonPath("$.categoria.nombre").isEqualTo("Muebles");
	}
	
	@Test
	public void crear2Test() {
		Categoria categoria = service.findCategoriaByNombre("Muebles").block();
		Producto producto = Producto.builder().nombre("mesa").precio(100.00).categoria(categoria).build();
		client.post().uri("/api/v2/productos")
		.contentType(MediaType.APPLICATION_JSON_UTF8)//del request con el cual enviamos el json para crear el flujo
		.accept(MediaType.APPLICATION_JSON_UTF8)//lo que esperamos, response
		.body(Mono.just(producto), Producto.class)
		.exchange()
		.expectStatus().isCreated()
		.expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
		.expectBody(Producto.class)
		.consumeWith(response -> {
			Producto p = response.getResponseBody();
			Assertions.assertThat(p.getId()).isNotEmpty();
			Assertions.assertThat(p.getNombre()).isEqualTo("mesa");
			Assertions.assertThat(p.getCategoria().getNombre()).isEqualTo("Muebles");
		});
	}
	
	@Test
	public void editarTest() {
		Producto producto = service.findByNombre("Sony Camara HD Digital").block();
		Categoria categoria = service.findCategoriaByNombre("Muebles").block();
		
		Producto productoEditado = Producto.builder().nombre("mesa").precio(100.00).categoria(categoria).build();
		
		client.put()
		.uri("/api/v2/productos/{id}", Collections.singletonMap("id", producto.getId()))
		.contentType(MediaType.APPLICATION_JSON_UTF8)//del request con el cual enviamos el json para crear el flujo
		.accept(MediaType.APPLICATION_JSON_UTF8)//lo que esperamos, response
		.body(Mono.just(productoEditado), Producto.class)
		.exchange()
		.expectStatus().isCreated()
		.expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
		.expectBody()
		.jsonPath("$.id").isNotEmpty()
		.jsonPath("$.nombre").isEqualTo("mesa")
		.jsonPath("$.categoria.nombre").isEqualTo("Muebles");
		
	}

}
