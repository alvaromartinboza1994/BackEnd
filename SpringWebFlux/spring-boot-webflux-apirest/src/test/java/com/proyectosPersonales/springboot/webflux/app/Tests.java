package com.proyectosPersonales.springboot.webflux.app;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;


import com.proyectosPersonales.springboot.webflux.app.models.documents.Producto;
import com.proyectosPersonales.springboot.webflux.app.models.service.ProductoService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //puerto aleatorio disponible
public class Tests {
	
	@Autowired
	private WebTestClient client;
	
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

}
