package com.proyectosPersonales.springboot.app.usuario.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.proyectosPersonales.springboot.app.commons.dto.Usuario;
import com.proyectosPersonales.springboot.app.commons.dto.UsuarioContrasena;

public interface UsuarioControllerI {
	
	@PostMapping("/registrarUsuario")
	public ResponseEntity<Usuario> registrarUsuario(@RequestBody UsuarioContrasena signup);
	
	@GetMapping("/accederUsuario")
	public ResponseEntity<String> accederUsuario(@RequestBody UsuarioContrasena signup);
	
	@GetMapping("/buscarUsuario/{codUsuario}")
	public ResponseEntity<Usuario> buscarUsuario(@PathVariable String codUsuario);
	
	@GetMapping("/welcome")
	public ResponseEntity<String> buscarUsuario();

}
