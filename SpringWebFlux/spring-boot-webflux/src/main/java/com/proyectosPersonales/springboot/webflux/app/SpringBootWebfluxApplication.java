package com.proyectosPersonales.springboot.webflux.app;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import com.proyectosPersonales.springboot.webflux.app.models.documents.Categoria;
import com.proyectosPersonales.springboot.webflux.app.models.documents.Producto;
import com.proyectosPersonales.springboot.webflux.app.models.services.ProductoServiceImpl;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@SpringBootApplication
@Slf4j
public class SpringBootWebfluxApplication implements CommandLineRunner {

	@Autowired
	private ProductoServiceImpl service;

	@Autowired
	private ReactiveMongoTemplate mongoTemplate;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebfluxApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// SOLO PARA DESARROLLO
		mongoTemplate.dropCollection("productos").subscribe();// para hacer drop de todos los productos actuales en bbdd
																// al iniciar la aplicacion
		mongoTemplate.dropCollection("categorias").subscribe();

		Categoria categoria1 = Categoria.builder().nombre("Electrónico").build();
		Categoria categoria2 = Categoria.builder().nombre("Deporte").build();
		Categoria categoria3 = Categoria.builder().nombre("Computación").build();
		Categoria categoria4 = Categoria.builder().nombre("Muebles").build();

		Flux.just(categoria1, categoria2, categoria3, categoria4)
		.flatMap(service::saveCategoria)
		.doOnNext(c -> {
			log.info("Categoria creada: " + c.getNombre() + " Id: " + c.getId());
		}).thenMany(
				Flux.just(Producto.builder().nombre("TV plana").precio(100D).categoria(categoria1).build(),
						Producto.builder().nombre("Movil").precio(200D).categoria(categoria2).build(),
						Producto.builder().nombre("Vaso").precio(2D).categoria(categoria4).build(),
						Producto.builder().nombre("Mesa").precio(275D).categoria(categoria4).build(),
						Producto.builder().nombre("Microhondas").precio(210D).categoria(categoria4).build())
				.flatMap(producto -> {
					producto.setCreateAt(new Date());
					return service.save(producto);
				})// hace lo mismo que map pero este esta preparado para trabajar con flux y mono
		).subscribe(producto -> log.info("Insert: " + producto.getId() + " " + producto.getNombre()));

	}

}
