package com.proyectosPersonales.springboot.app.gastos.service.interfaces;

import com.proyectosPersonales.springboot.app.gastos.dto.UsuarioContrasena;

import org.springframework.http.ResponseEntity;

import com.proyectosPersonales.springboot.app.gastos.dto.Usuario;

public interface UsuarioService {
	
	Usuario buscarPorCodUsuario(String codUsuario);
	
	Usuario buscarPorIdUsuario(Integer idUsuario);

	Usuario guardarUsuario(UsuarioContrasena signup);
	
	Usuario actualizarUsuario(Usuario usuario);
	
	ResponseEntity<String> accederUsuario(UsuarioContrasena signup);

}
