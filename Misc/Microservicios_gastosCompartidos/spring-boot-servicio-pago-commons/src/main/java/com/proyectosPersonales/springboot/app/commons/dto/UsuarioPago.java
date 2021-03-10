package com.proyectosPersonales.springboot.app.commons.dto;

import java.util.Calendar;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UsuarioPago {
	
	private String codUsuario;
	
	private Double importe; 

	private String descripcion;
	
	private Calendar fecha;

}
