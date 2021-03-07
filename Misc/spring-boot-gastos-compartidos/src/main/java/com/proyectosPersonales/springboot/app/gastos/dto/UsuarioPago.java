package com.proyectosPersonales.springboot.app.gastos.dto;

import lombok.Data;

@Data
public class UsuarioPago {
	
	private Usuario usuario;
	
	private Pago pago;

}
