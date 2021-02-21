package com.proyectosPersonales.springboot.webflux.app.models.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "categorias")
@Data
@NoArgsConstructor
public class Categoria {
	
	@Id
	private String id;
	
	private String nombre;

	public Categoria(String nombre) {
		this.nombre = nombre;
	}
	
	

}
