package com.proyectosPersonales.springboot.webflux.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

import org.springframework.web.reactive.function.server.RouterFunction;

import org.springframework.web.reactive.function.server.ServerResponse;

import com.proyectosPersonales.springboot.webflux.app.handler.ProductoHandler;
import com.proyectosPersonales.springboot.webflux.app.models.documents.Producto;
import com.proyectosPersonales.springboot.webflux.app.models.service.ProductoService;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterFunctionConfig {
	


	@Bean
	public RouterFunction<ServerResponse> routes(ProductoHandler handler) {
		
		return route(GET("/api/v2/productos").or(GET("/api/v3/productos")),
				/*request ->*/handler::listar);
	}
}
