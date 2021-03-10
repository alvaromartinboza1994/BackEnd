package com.proyectosPersonales.springboot.app.commons;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class SpringBootServicioGastosEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootServicioGastosEurekaServerApplication.class, args);
	}

}
