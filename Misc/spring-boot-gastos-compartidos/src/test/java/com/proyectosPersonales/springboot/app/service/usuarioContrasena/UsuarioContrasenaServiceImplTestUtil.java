package com.proyectosPersonales.springboot.app.service.usuarioContrasena;

import static com.proyectosPersonales.springboot.app.service.usuario.UsuarioServiceImplTestUtil.crearUsuarioCorrecto;

import com.proyectosPersonales.springboot.app.gastos.dto.UsuarioContrasena;

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
