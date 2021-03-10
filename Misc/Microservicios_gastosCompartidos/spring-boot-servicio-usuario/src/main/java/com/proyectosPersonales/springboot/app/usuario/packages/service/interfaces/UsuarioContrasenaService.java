package com.proyectosPersonales.springboot.app.usuario.packages.service.interfaces;

import com.proyectosPersonales.springboot.app.commons.dto.UsuarioContrasena;
import com.proyectosPersonales.springboot.app.commons.dto.Usuario;

public interface UsuarioContrasenaService {
	
	UsuarioContrasena guardarUsuarioContrasena(UsuarioContrasena signup);

	UsuarioContrasena buscarPorUsuarioCodUsuario(String codUsuario);
}
