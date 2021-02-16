package com.proyectosPersonales.springboot.web.app.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
	
	private String nombre;
	private String apellido;
	private String email;

}
