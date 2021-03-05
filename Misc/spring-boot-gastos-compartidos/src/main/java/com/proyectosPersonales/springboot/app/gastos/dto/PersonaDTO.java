package com.proyectosPersonales.springboot.app.gastos.dto;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Persona")
public class PersonaDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idPersona;

	private String nombre;

	private String apellidos;

}