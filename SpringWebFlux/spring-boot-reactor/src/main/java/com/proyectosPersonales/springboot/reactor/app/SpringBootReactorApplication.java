package com.proyectosPersonales.springboot.reactor.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.proyectosPersonales.springboot.reactor.app.Models.Usuario;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

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
		// creacion del primer observable
		Flux<Usuario> nombres = Flux.just("Alvaro Martín", "Reme Boza", "Jose Joaquin Martín", "Pepa Muñoz", "Pepa Pig", "Juan Mengano", "Tania Martín", "Carmen Barrero")
				.map(nombre -> new Usuario(nombre.split(" ")[0].toUpperCase(), nombre.split(" ")[1].toUpperCase()))
				.filter(usuario -> { return usuario.getNombre().toLowerCase().equals("pepa"); })
				.doOnNext(usuario -> {
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
		
		nombres.subscribe(e -> log.info(e.toString()), error -> log.error(error.getMessage()), new Runnable() {

			@Override
			public void run() {
				log.info("Ha finalizado la ejecución del observable con éxito!");
			}
		});// consume contenido del publiser.
	}

}
