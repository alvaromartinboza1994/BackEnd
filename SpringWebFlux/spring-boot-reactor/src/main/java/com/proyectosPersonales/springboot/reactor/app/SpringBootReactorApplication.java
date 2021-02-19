package com.proyectosPersonales.springboot.reactor.app;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.proyectosPersonales.springboot.reactor.app.Models.Comentarios;
import com.proyectosPersonales.springboot.reactor.app.Models.Usuario;
import com.proyectosPersonales.springboot.reactor.app.Models.UsuarioComentarios;
import com.sun.jdi.InternalException;

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
		ejemploIntervalDesdeCreate();
	}
	
	public void ejemploIntervalDesdeCreate() {
		Flux.create(emmitter -> {
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				private Integer contador = 0;
				@Override
				public void run() {
					emmitter.next(++contador);
					if(contador == 10) {
						timer.cancel();
						emmitter.complete();
					}
					if(contador == 5) {
						timer.cancel();
						emmitter.error(new InterruptedException("Error, se ha detenido el flux en 5!"));
					}
				}
			}, 1000, 1000);
		})
				/*
				 * .doOnNext(next -> log.info(next.toString())) .doOnComplete(() ->
				 * log.info("Hemos terminado"))
				 */
		.subscribe(next -> log.info(next.toString()), 
				error -> log.error(error.getMessage()),
				() -> log.info("Hemos terminado"));
	}
	
	public void ejemploDelayElements() throws InterruptedException {//alternativa para retrasar hilos
		Flux<Integer> rango = Flux.range(1, 12)
				.delayElements(Duration.ofSeconds(1))
				.doOnNext(i -> log.info(i.toString()));
		
		rango.blockLast();
		
		//rango.subscribe();
		//Thread.sleep(13000);
	}
	
	public void ejemploIntervalInfinito() throws InterruptedException {
		CountDownLatch latch = new CountDownLatch(1); //comienza en 1
		
		Flux.interval(Duration.ofSeconds(1))
		.doOnTerminate(() -> latch.countDown())//decrementa latch.  este evento se ejecutara falle o no falle
		.flatMap(i -> {
			if(i >= 5) {
				return Flux.error(new InternalException("Solo hasta 5!"));
			} else {
				return Flux.just(i); //observable con el valor que se esta emitiendo
			}
		})
		.map(i -> "Hola" + i)
		//.doOnNext(s -> log.info(s))
		.retry(2) //si hay un error lo intenta dos veces mas
		.subscribe(s -> log.info(s), e -> log.error(e.getMessage()));
		
		latch.await();//espera hasta que el contador llegue a 0, libera el thread
	}
	
	public void ejemploInterval() {//no se mostrara en el terminal. El flujo se sigue ejecutando en segundo plano, se sigue ejecutando en la maquina virtual de java. CARACTERISTICA DE PROGRAMACIÓN REACTIVA -> SE EJECUTAN EN DIFERENTES HILOS
		Flux<Integer> rango = Flux.range(1, 12);
		Flux<Long> retraso = Flux.interval(Duration.ofSeconds(1));
		
		rango.zipWith(retraso, (ra, re) -> ra)
		.doOnNext(i -> log.info(i.toString()))
		//.blockLast();//NO ES RECOMENDABLE, para ver el bloqueo que se produce ponemos blocklast en vez de subscribe
		.subscribe();
	}
	
	public void ejemploZipWithRangos() {
		Flux<Integer> rangos = Flux.range(0, 4);
				Flux.just(1, 2, 3, 4)
		.map(i -> (i*2))
		.zipWith(rangos, (uno, dos) -> String.format("Primer Flux: %d, Segundo Flux: %d", uno, dos))
		.subscribe(texto -> log.info(texto));
	}

	public void ejemploUsuarioComentariosZipWith() {
		Mono<Usuario> usuarioMono = Mono.fromCallable(() -> Usuario.builder().nombre("Homer").apellido("Simpson").build());
	
		Mono<Comentarios> comentariosUsuarioMono = Mono.fromCallable(() -> {
			return Comentarios.builder().comentarios(Arrays.asList("Hola qué tal?", "Yo bien, y tu?", "Bien bien jeje")).build();
		});
		
		Mono<UsuarioComentarios> usuarioConComentarios = usuarioMono.zipWith(comentariosUsuarioMono, (usuario, comentariosUsuario) -> UsuarioComentarios.builder().usuario(usuario).comentarios(comentariosUsuario).build());
		
		usuarioConComentarios.subscribe(uc -> log.info(uc.toString())); //convertir a un flujo combinado a usuarioComentarios. ALTERNATIVA A FLATMAP
	}
	
	public void ejemploUsuarioComentariosZipWithForma2() {
		Mono<Usuario> usuarioMono = Mono.fromCallable(() -> Usuario.builder().nombre("Homer").apellido("Simpson").build());
	
		Mono<Comentarios> comentariosUsuarioMono = Mono.fromCallable(() -> {
			return Comentarios.builder().comentarios(Arrays.asList("Hola qué tal?", "Yo bien, y tu?", "Bien bien jeje")).build();
		});
		
		Mono<UsuarioComentarios> usuarioConComentarios = usuarioMono
				.zipWith(comentariosUsuarioMono)
				.map(tuple -> {
					Usuario u = tuple.getT1();
					Comentarios c = tuple.getT2();
					return UsuarioComentarios.builder().usuario(u).comentarios(c).build();
				});
		
		usuarioConComentarios.subscribe(uc -> log.info(uc.toString())); //convertir a un flujo combinado a usuarioComentarios. ALTERNATIVA A FLATMAP
	}

	public void ejemploUsuarioComentariosFlatMap() {
		Mono<Usuario> usuarioMono = Mono
				.fromCallable(() -> Usuario.builder().nombre("Homer").apellido("Simpson").build());

		Mono<Comentarios> comentariosUsuarioMono = Mono.fromCallable(() -> {
			return Comentarios.builder().comentarios(Arrays.asList("Hola qué tal?", "Yo bien, y tu?", "Bien bien jeje"))
					.build();
		});

		usuarioMono
				.flatMap(usuario -> comentariosUsuarioMono
						.map(comentario -> UsuarioComentarios.builder().usuario(usuario).comentarios(comentario)))
				.subscribe(uc -> log.info(uc.toString())); // convertir a un flujo combinado de usuarioComentarios
	}

	public void ejemploToCollectList() throws Exception {
		// creacion del primer observable
		List<Usuario> usuariosList = Arrays.asList(Usuario.builder().nombre("Álvaro").apellido("Martín").build(),
				Usuario.builder().nombre("Reme").apellido("Boza").build(),
				Usuario.builder().nombre("José Joaquín").apellido("Martín").build(),
				Usuario.builder().nombre("Pepa").apellido("Muñoz").build(),
				Usuario.builder().nombre("Pepa").apellido("Pig").build(),
				Usuario.builder().nombre("Juan").apellido("Mengano").build());

		Flux.fromIterable(usuariosList).collectList()// convierte a un solo objeto que seria la lista de usuarios
				.subscribe(lista -> {// se emite UNA SOLA VEZ
					lista.forEach(item -> log.info(item.toString()));
				});
	}

	public void ejemploToString() throws Exception {
		// creacion del primer observable
		List<Usuario> usuariosList = Arrays.asList(Usuario.builder().nombre("Álvaro").apellido("Martín").build(),
				Usuario.builder().nombre("Reme").apellido("Boza").build(),
				Usuario.builder().nombre("José Joaquín").apellido("Martín").build(),
				Usuario.builder().nombre("Pepa").apellido("Muñoz").build(),
				Usuario.builder().nombre("Pepa").apellido("Pig").build(),
				Usuario.builder().nombre("Juan").apellido("Mengano").build());

		Flux.fromIterable(usuariosList).map(
				usuario -> usuario.getNombre().toUpperCase().concat(" ").concat(usuario.getApellido().toUpperCase()))
				.flatMap(nombre -> { // tipo filter
					if (nombre.contains("pepa".toUpperCase())) {
						return Mono.just(nombre);// como es un solo elemento devolvemos Mono -> devolvemos un
													// observable, fusionandolo al mimso stream. REDUCE EL STREAM ACTUAL
					} else {
						return Mono.empty();
					}
				}).map(nombre -> {
					return nombre.toLowerCase();
				}).subscribe(nombre -> log.info(nombre.toString()));// consume contenido del publiser.;
	}

	public void ejemploFlatMap() throws Exception {
		// creacion del primer observable
		List<String> usuariosList = Arrays.asList("Alvaro Martín", "Reme Boza", "Jose Joaquin Martín", "Pepa Muñoz",
				"Pepa Pig", "Juan Mengano", "Tania Martín", "Carmen Barrero");

		Flux.fromIterable(usuariosList)
				.map(nombre -> new Usuario(nombre.split(" ")[0].toUpperCase(), nombre.split(" ")[1].toUpperCase()))
				.flatMap(usuario -> { // tipo filter
					if (usuario.getNombre().equalsIgnoreCase("pepa")) {
						return Mono.just(usuario);// como es un solo elemento devolvemos Mono -> devolvemos un
													// observable, fusionandolo al mimso stream. REDUCE EL STREAM ACTUAL
					} else {
						return Mono.empty();
					}
				}).map(usuario -> {
					String nombre = usuario.getNombre().toLowerCase();
					usuario.setNombre(nombre);
					return usuario;
				}).subscribe(usuario -> log.info(usuario.toString()));// consume contenido del publiser.;
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
