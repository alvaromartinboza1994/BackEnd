package com.proyectosPersonales.springboot.app.gastos.controller.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyectosPersonales.springboot.app.gastos.dto.Usuario;
import com.proyectosPersonales.springboot.app.gastos.dto.UsuarioContrasena;

public interface UsuarioControllerI {
	
	@PostMapping("/registrarUsuario")
	public ResponseEntity<Usuario> registrarUsuario(@RequestBody UsuarioContrasena signup);
	
	@GetMapping("/accederUsuario")
	public ResponseEntity<String> accederUsuario(@RequestBody UsuarioContrasena signup);
	
	@GetMapping("/buscarUsuario/{codUsuario}")
	public ResponseEntity<Usuario> buscarUsuario(@PathVariable String codUsuario);

}
