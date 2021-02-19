package com.proyectosPersonales.springboot.reactor.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@SpringBootApplication
@Slf4j
public class SpringBootReactorApplication implements CommandLineRunner{ //para que la aplicacion sea de consola o lineas de comando tiene que implementar esta interfaz

	public static void main(String[] args) {
		SpringApplication.run(SpringBootReactorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//creacion del primer observable
		Flux<String> nombres = Flux.just("Alvaro", "Reme", "Jose Joaquin", "" , "Tania", "Carmen")
				.doOnNext(elemento -> {
					if(elemento.isEmpty()) {
						throw new RuntimeException("Nombres no pueden ser vacíos"); //manejo de errors
					} else {
						System.out.println(elemento);
					}
				}); //publiser = observable. cada vez que se emita un elemento, se imprime en pantalla
		nombres.subscribe(log::info, error -> log.error(error.getMessage()));//sin esto no se imprime. 
	}

}
