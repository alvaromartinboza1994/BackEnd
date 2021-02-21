package com.proyectosPersonales.springboot.webflux.app;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import com.proyectosPersonales.springboot.webflux.app.models.dao.ProductoDao;
import com.proyectosPersonales.springboot.webflux.app.models.documents.Producto;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@SpringBootApplication
@Slf4j
public class SpringBootWebfluxApplication implements CommandLineRunner {
	
	@Autowired
	private ProductoDao dao;
	
	@Autowired
	private ReactiveMongoTemplate mongoTemplate;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebfluxApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		//SOLO PARA DESARROLLO
		mongoTemplate.dropCollection("productos").subscribe();//para hacer drop de todos los productos actuales en bbdd al iniciar la aplicacion
		
		Flux.just(Producto.builder().nombre("TV plana").precio(100D).build(),
				Producto.builder().nombre("Movil").precio(200D).build(),
				Producto.builder().nombre("Vaso").precio(2D).build(),
				Producto.builder().nombre("Mesa").precio(275D).build(),
				Producto.builder().nombre("Microhondas").precio(210D).build())
		.flatMap(producto ->  {
			producto.setCreateAt(new Date());
			return dao.save(producto);
		})//hace lo mismo que map pero este esta preparado para trabajar con flux y mono
		.subscribe(producto -> log.info("Insert: " + producto.getId() + " " + producto.getNombre()));

	}

}
