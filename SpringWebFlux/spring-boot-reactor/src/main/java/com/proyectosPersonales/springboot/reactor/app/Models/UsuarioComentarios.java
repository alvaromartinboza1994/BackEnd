package com.proyectosPersonales.springboot.reactor.app.Models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioComentarios {

	private Usuario usuario;
	private Comentarios comentarios;
	
	
	@Override
	public String toString() {
		return "UsuarioComentarios [usuario=" + usuario + ", comentarios=" + comentarios + "]";
	}
	
	
}
