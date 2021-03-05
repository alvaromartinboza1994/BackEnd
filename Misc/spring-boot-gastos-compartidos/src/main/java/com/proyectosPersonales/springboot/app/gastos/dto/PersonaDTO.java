package com.proyectosPersonales.springboot.app.gastos.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonaDTO {

	private String nombre;
	
	private String apellidos;
	
	private List<AmigoDTO> listaAmigos;
	
	public PersonaDTO(String nombre, String apellidos) {
		this.nombre = nombre;
		this.apellidos = apellidos;
	}
	
}
