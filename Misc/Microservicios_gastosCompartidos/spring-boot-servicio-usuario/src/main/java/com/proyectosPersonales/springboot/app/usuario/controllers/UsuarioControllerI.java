package com.proyectosPersonales.springboot.app.usuario.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.proyectosPersonales.springboot.app.commons.dto.Usuario;
import com.proyectosPersonales.springboot.app.commons.dto.UsuarioContrasena;

public interface UsuarioControllerI {
	
	@PostMapping("/usuario/guardarUsuario")
	public ResponseEntity<Usuario> guardarUsuario(@RequestBody UsuarioContrasena signup);
	
	@PutMapping("/usuario/actualizarUsuario")
	public ResponseEntity<Usuario> actualizarUsuario(@RequestBody Usuario usuario);
	
	@PostMapping("/usuario/accederUsuario")
	public ResponseEntity<String> accederUsuario(@RequestBody UsuarioContrasena signup);
	
	@GetMapping("/usuario/buscarPorCodUsuario/{codUsuario}")
	public ResponseEntity<Usuario> buscarPorCodUsuario(@PathVariable String codUsuario);
	
	@GetMapping("/usuario/welcome")
	public ResponseEntity<String> welcome();

}
