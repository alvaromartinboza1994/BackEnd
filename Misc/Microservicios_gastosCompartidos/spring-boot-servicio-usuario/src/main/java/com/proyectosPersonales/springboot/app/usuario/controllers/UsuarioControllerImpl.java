package com.proyectosPersonales.springboot.app.usuario.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyectosPersonales.springboot.app.commons.dto.Usuario;
import com.proyectosPersonales.springboot.app.commons.dto.UsuarioContrasena;
import com.proyectosPersonales.springboot.app.commons.dto.UsuarioActualizar;
import com.proyectosPersonales.springboot.app.usuario.packages.service.interfaces.UsuarioService;

import lombok.extern.slf4j.Slf4j;

@RestController
public class UsuarioControllerImpl implements UsuarioControllerI{

	@Autowired
	private UsuarioService usuarioService;
	
	@Override
	public ResponseEntity<Usuario> guardarUsuario(UsuarioContrasena signup) {
		return new ResponseEntity<>(usuarioService.guardarUsuario(signup), HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<String> accederUsuario(String codUsuario, String contrasena) {
		return usuarioService.accederUsuario(UsuarioContrasena.builder()
					.usuario(Usuario.builder()
							.codUsuario(codUsuario)
							.build())
					.contraseña(contrasena)
				.build());
	}
	
	@Override
	public ResponseEntity<Usuario> buscarPorCodUsuario(String codUsuario) {
		return new ResponseEntity<>(usuarioService.buscarPorCodUsuario(codUsuario), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> welcome() {
		return new ResponseEntity<>("Bienvenido a la aplicación de Gastos Compartidos", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Usuario> actualizarUsuario(UsuarioActualizar usuario) {
		return new ResponseEntity<>(usuarioService.actualizarUsuario(usuario), HttpStatus.OK);
	}

}
