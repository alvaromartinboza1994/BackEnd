package com.proyectosPersonales.springboot.app.usuario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EntityScan({"com.proyectosPersonales.springboot.app.commons.dto", "com.proyectosPersonales.springboot.app.commons.exception"})
public class SpringBootServicioUsuarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootServicioUsuarioApplication.class, args);
	}

}
