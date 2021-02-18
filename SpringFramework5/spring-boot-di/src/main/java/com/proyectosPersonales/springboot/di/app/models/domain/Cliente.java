package com.proyectosPersonales.springboot.di.app.models.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class Cliente {

	@Value("${cliente.nombre}")
	private String nombre;
	
	@Value("${cliente.apellidos}")
	private String apellido;

}
