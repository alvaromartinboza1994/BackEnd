package com.proyectosPersonales.springboot.app.gastos.service.interfaces;

import org.springframework.http.ResponseEntity;

import com.proyectosPersonales.springboot.app.gastos.dto.Usuario;
import com.proyectosPersonales.springboot.app.gastos.dto.UsuarioActualizar;
import com.proyectosPersonales.springboot.app.gastos.dto.UsuarioContrasena;

public interface UsuarioService {
	
	Usuario buscarPorCodUsuario(String codUsuario);
	
	Usuario buscarPorIdUsuario(Integer idUsuario);

	Usuario guardarUsuario(UsuarioContrasena signup);
	
	Usuario actualizarUsuario(UsuarioActualizar usuario);
	
	ResponseEntity<String> accederUsuario(UsuarioContrasena signup);

}
