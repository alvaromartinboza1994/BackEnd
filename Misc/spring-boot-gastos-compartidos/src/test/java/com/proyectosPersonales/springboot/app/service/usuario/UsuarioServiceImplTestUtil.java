package com.proyectosPersonales.springboot.app.service.usuario;

import java.util.Optional;

import com.proyectosPersonales.springboot.app.gastos.dto.Usuario;

public class UsuarioServiceImplTestUtil {
	
	public static Usuario crearUsuarioCorrecto() {
		return Usuario.builder()
				.nombre("N")
				.apellidos("AP1")
				.codUsuario("NAP1")
				.build();
	}
	
	public static Usuario crearUsuarioCorrecto_ConGrupo() {
		return Usuario.builder()
				.nombre("N")
				.apellidos("AP1")
				.codUsuario("NAP1")
				.miGrupo("misAmigos")
				.build();
	}
	
	public static Optional<Usuario> crearUsuarioCorrecto_Optional() {
		return Optional.of(Usuario.builder()
				.nombre("N")
				.apellidos("AP1")
				.codUsuario("NAP1")
				.build());
	}
	
	public static Usuario crearUsuarioCorrecto2() {
		return Usuario.builder()
				.nombre("N2")
				.apellidos("AP2")
				.codUsuario("NAP2")
				.build();
	}
	
	public static Usuario crearUsuarioVacio() {
		return Usuario.builder()
				.build();
	}

    
}
