package com.proyectosPersonales.springboot.reactor.app.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Usuario {

	public String nombre;
	public String apellido;
}
