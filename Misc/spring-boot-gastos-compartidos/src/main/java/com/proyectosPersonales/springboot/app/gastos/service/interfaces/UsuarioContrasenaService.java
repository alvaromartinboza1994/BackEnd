package com.proyectosPersonales.springboot.app.gastos.service.interfaces;

import com.proyectosPersonales.springboot.app.gastos.dto.UsuarioContrasena;
import com.proyectosPersonales.springboot.app.gastos.dto.Usuario;

public interface UsuarioContrasenaService {
	
	UsuarioContrasena guardarUsuarioContrasena(UsuarioContrasena signup);

	UsuarioContrasena buscarPorUsuarioCodUsuario(String codUsuario);
}
