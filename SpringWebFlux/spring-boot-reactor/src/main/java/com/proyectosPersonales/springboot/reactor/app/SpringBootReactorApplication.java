package com.proyectosPersonales.springboot.reactor.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.proyectosPersonales.springboot.reactor.app.Models.Comentarios;
import com.proyectosPersonales.springboot.reactor.app.Models.Usuario;
import com.proyectosPersonales.springboot.reactor.app.Models.UsuarioComentarios;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootApplication
@Slf4j
public class SpringBootReactorApplication implements CommandLineRunner { // para que la aplicacion sea de consola o
																			// lineas de comando tiene que implementar
																			// esta interfaz

	public static void main(String[] args) {
		SpringApplication.run(SpringBootReactorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ejemploUsuarioComentariosFlatMap();
	}
	
	public void ejemploUsuarioComentariosFlatMap() {
		Mono<Usuario> usuarioMono = Mono.fromCallable(() -> Usuario.builder().nombre("Homer").apellido("Simpson").build());
	
		Mono<Comentarios> comentariosUsuarioMono = Mono.fromCallable(() -> {
			return Comentarios.builder().comentarios(Arrays.asList("Hola qué tal?", "Yo bien, y tu?", "Bien bien jeje")).build();
		});
		
		usuarioMono.flatMap(usuario -> comentariosUsuarioMono
				.map(comentario -> UsuarioComentarios.builder().usuario(usuario).comentarios(comentario)))
		.subscribe(uc -> log.info(uc.toString())); //convertir a un flujo combinado de usuarioComentarios
	}
	
	public void ejemploToCollectList() throws Exception {
		// creacion del primer observable
		List<Usuario> usuariosList = Arrays.asList(Usuario.builder().nombre("Álvaro").apellido("Martín").build(), Usuario.builder().nombre("Reme").apellido("Boza").build(),
				Usuario.builder().nombre("José Joaquín").apellido("Martín").build(), Usuario.builder().nombre("Pepa").apellido("Muñoz").build(),
				Usuario.builder().nombre("Pepa").apellido("Pig").build(), Usuario.builder().nombre("Juan").apellido("Mengano").build());

		Flux.fromIterable(usuariosList)
		.collectList()//convierte a un solo objeto que seria la lista de usuarios
		.subscribe(lista -> {//se emite UNA SOLA VEZ
			lista.forEach(item -> log.info(item.toString()));
		});
	}
	
	public void ejemploToString() throws Exception {
		// creacion del primer observable
		List<Usuario> usuariosList = Arrays.asList(Usuario.builder().nombre("Álvaro").apellido("Martín").build(), Usuario.builder().nombre("Reme").apellido("Boza").build(),
				Usuario.builder().nombre("José Joaquín").apellido("Martín").build(), Usuario.builder().nombre("Pepa").apellido("Muñoz").build(),
				Usuario.builder().nombre("Pepa").apellido("Pig").build(), Usuario.builder().nombre("Juan").apellido("Mengano").build());

		Flux.fromIterable(usuariosList)
				.map(usuario -> usuario.getNombre().toUpperCase().concat(" ").concat(usuario.getApellido().toUpperCase()))
				.flatMap(nombre -> { //tipo filter
					if(nombre.contains("pepa".toUpperCase())) {
						return Mono.just(nombre);//como es un solo elemento devolvemos Mono -> devolvemos un observable, fusionandolo al mimso stream. REDUCE EL STREAM ACTUAL
					} else {
						return Mono.empty();
					}
				})
				.map(nombre -> {
					return nombre.toLowerCase();
				})
				.subscribe(nombre -> log.info(nombre.toString()));// consume contenido del publiser.;
	}

	public void ejemploFlatMap() throws Exception {
		// creacion del primer observable
		List<String> usuariosList = Arrays.asList("Alvaro Martín", "Reme Boza", "Jose Joaquin Martín", "Pepa Muñoz",
				"Pepa Pig", "Juan Mengano", "Tania Martín", "Carmen Barrero");

		Flux.fromIterable(usuariosList)
				.map(nombre -> new Usuario(nombre.split(" ")[0].toUpperCase(), nombre.split(" ")[1].toUpperCase()))
				.flatMap(usuario -> { //tipo filter
					if(usuario.getNombre().equalsIgnoreCase("pepa")) {
						return Mono.just(usuario);//como es un solo elemento devolvemos Mono -> devolvemos un observable, fusionandolo al mimso stream. REDUCE EL STREAM ACTUAL
					} else {
						return Mono.empty();
					}
				})
				.map(usuario -> {
					String nombre = usuario.getNombre().toLowerCase();
					usuario.setNombre(nombre);
					return usuario;
				})
				.subscribe(usuario -> log.info(usuario.toString()));// consume contenido del publiser.;
	}

	public void ejemploIterable() throws Exception {
		// creacion del primer observable
		List<String> usuariosList = Arrays.asList("Alvaro Martín", "Reme Boza", "Jose Joaquin Martín", "Pepa Muñoz",
				"Pepa Pig", "Juan Mengano", "Tania Martín", "Carmen Barrero");

		Flux<String> nombres = Flux.fromIterable(
				usuariosList); /*
								 * Flux.just("Alvaro Martín", "Reme Boza", "Jose Joaquin Martín", "Pepa Muñoz",
								 * "Pepa Pig", "Juan Mengano", "Tania Martín", "Carmen Barrero");
								 */

		// separando la declaracion inicial del resto, se convierte en un flujo
		// diferente
		Flux<Usuario> usuarios = nombres
				.map(nombre -> new Usuario(nombre.split(" ")[0].toUpperCase(), nombre.split(" ")[1].toUpperCase()))
				.filter(usuario -> {
					return usuario.getNombre().toLowerCase().equals("pepa");
				}).doOnNext(usuario -> {
					if (usuario == null) {
						throw new RuntimeException("Nombres no pueden ser vacíos"); // manejo de errors
					} else {
						System.out.println(usuario.getNombre().concat(" ").concat(usuario.getApellido()));
					}
				}) // publiser = observable. cada vez que se emita un elemento, se imprime en
					// pantalla
				.map(usuario -> {
					String nombre = usuario.getNombre().toLowerCase();
					usuario.setNombre(nombre);
					return usuario;
				});
		/*
		 * log.info("FLUJO NOMBRES"); nombres.subscribe(e -> log.info(e.toString()),
		 * error -> log.error(error.getMessage()), new Runnable() {
		 * 
		 * @Override public void run() {
		 * log.info("Ha finalizado la ejecución del observable con éxito!"); } });//
		 * consume contenido del publiser.
		 */
		log.info("FLUJO USUARIOS");
		usuarios.subscribe(e -> log.info(e.toString()), error -> log.error(error.getMessage()), new Runnable() {

			@Override
			public void run() {
				log.info("Ha finalizado la ejecución del observable con éxito!");
			}
		});// consume contenido del publiser.
	}

}
