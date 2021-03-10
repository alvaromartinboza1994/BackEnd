package com.proyectosPersonales.springboot.app.pago;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableCircuitBreaker
@EnableEurekaClient
@EnableFeignClients //Permite inyectar clientes en controladores u otros componentes de spring
@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})//deshabilitamos el autoconfiguracion del datasource
@EntityScan({"com.proyectosPersonales.springboot.app.commons.dto", "com.proyectosPersonales.springboot.app.commons.exception"})
public class SpringBootServicioPagoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootServicioPagoApplication.class, args);
	}

}
