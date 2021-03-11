package com.proyectosPersonales.springboot.app.gastos.service.interfaces;

import com.proyectosPersonales.springboot.app.gastos.dto.UsuarioContrasena;

public interface UsuarioContrasenaService {
	
	UsuarioContrasena guardarUsuarioContrasena(UsuarioContrasena signup);

	UsuarioContrasena buscarPorUsuarioCodUsuario(String codUsuario);
}
