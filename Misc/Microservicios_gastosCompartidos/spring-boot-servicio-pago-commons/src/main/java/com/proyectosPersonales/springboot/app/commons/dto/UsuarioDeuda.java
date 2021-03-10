package com.proyectosPersonales.springboot.app.commons.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDeuda {
	
	private Deuda deuda;
	
	private String deudor;
}
