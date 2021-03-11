package com.proyectosPersonales.springboot.app.gastos.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioActualizar {

	private Usuario nuevo;
	
	private String codUsuario_Antiguo;
}
