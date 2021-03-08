package com.proyectosPersonales.springboot.app.service.usuario;

import com.proyectosPersonales.springboot.app.gastos.dto.Usuario;

public class UsuarioServiceImplTestUtil {
	
	public static Usuario crearUsuarioCorrecto() {
		return Usuario.builder()
				.idUsuario(1)
				.nombre("N")
				.apellidos("AP1")
				.codUsuario("NAP1")
				.build();
	}

    
}
