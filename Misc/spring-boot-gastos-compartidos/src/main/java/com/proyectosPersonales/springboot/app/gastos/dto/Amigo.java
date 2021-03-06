package com.proyectosPersonales.springboot.app.gastos.dto;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name = "amigo")
public class Amigo implements Serializable{
	
	private static final long serialVersionUID = -4192706905476328295L;

	@EmbeddedId
    private UsuarioPk idAmigo;
}
