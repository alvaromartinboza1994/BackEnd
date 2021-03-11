package com.proyectosPersonales.springboot.app.usuario.packages.service.interfaces;

import com.proyectosPersonales.springboot.app.commons.dto.UsuarioContrasena;
import com.proyectosPersonales.springboot.app.commons.dto.UsuarioActualizar;

import org.springframework.http.ResponseEntity;

import com.proyectosPersonales.springboot.app.commons.dto.Usuario;

public interface UsuarioService {
	
Usuario buscarPorCodUsuario(String codUsuario);
	
	Usuario buscarPorIdUsuario(Integer idUsuario);

	Usuario guardarUsuario(UsuarioContrasena signup);
	
	Usuario actualizarUsuario(UsuarioActualizar usuario);
	
	ResponseEntity<String> accederUsuario(UsuarioContrasena signup);

}
