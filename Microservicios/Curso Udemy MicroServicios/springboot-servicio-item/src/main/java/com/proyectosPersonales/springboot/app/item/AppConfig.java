package com.proyectosPersonales.springboot.app.item;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

	@Bean("clienteRest")
	public RestTemplate registrarRestTemplate() { //cliente http para trabajar con resttempalte, para trabajar con otros servicios
		return new RestTemplate();
	}
}
