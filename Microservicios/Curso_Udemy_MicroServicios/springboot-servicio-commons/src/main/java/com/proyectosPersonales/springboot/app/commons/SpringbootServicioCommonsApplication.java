package com.proyectosPersonales.springboot.app.commons;

import javax.activation.DataSource;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})//deshabilitamos el autoconfiguracion del datasource
public class SpringbootServicioCommonsApplication {

}
