package com.proyectosPersonales.springboot.app.usuario.usuarioContrasena;

import static com.proyectosPersonales.springboot.app.usuario.usuario.UsuarioServiceImplTestUtil.*;

import com.proyectosPersonales.springboot.app.commons.dto.UsuarioContrasena;


public class UsuarioContrasenaServiceImplTestUtil {
	
    public static UsuarioContrasena crearUsuarioContrasenaCorrecto() {
		return UsuarioContrasena.builder()
				.idLogin(1)
				.contraseña("1234")
				.usuario(crearUsuarioCorrecto())
				.build();
	}
    
    public static UsuarioContrasena crearUsuarioContrasenaCorrecto2() {
		return UsuarioContrasena.builder()
				.idLogin(1)
				.contraseña("4567")
				.usuario(crearUsuarioCorrecto())
				.build();
	}
	
}
