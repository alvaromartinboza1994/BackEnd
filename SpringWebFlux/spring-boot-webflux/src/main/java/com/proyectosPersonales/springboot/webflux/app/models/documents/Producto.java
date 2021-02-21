package com.proyectosPersonales.springboot.webflux.app.models.documents;

import java.sql.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document(collection = "productos")
public class Producto {
	
	@Id
	private String id;
	
	private String nombre;
	private Double precio;
	private Date createAt;
	
}
