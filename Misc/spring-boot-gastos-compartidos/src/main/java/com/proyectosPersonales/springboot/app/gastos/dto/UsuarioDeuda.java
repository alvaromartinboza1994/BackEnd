package com.proyectosPersonales.springboot.app.gastos.dto;

import java.util.Calendar;
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
	
	private List<Deuda> deudas;
	
	private String codUsuario;
}
