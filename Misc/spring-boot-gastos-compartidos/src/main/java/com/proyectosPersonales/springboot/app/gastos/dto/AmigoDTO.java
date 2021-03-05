package com.proyectosPersonales.springboot.app.gastos.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Amigo")
public class AmigoDTO extends PersonaDTO{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idAmigo;
		
}
