package com.proyectosPersonales.springboot.app.gastos.controller.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyectosPersonales.springboot.app.gastos.dto.Usuario;
import com.proyectosPersonales.springboot.app.gastos.dto.UsuarioContrasena;

public interface UsuarioControllerI {
	
	@PostMapping("/guardarUsuario")
	public ResponseEntity<Usuario> guardarUsuario(@RequestBody UsuarioContrasena signup);
	
	@PutMapping("/usuario/actualizarUsuario")
	public ResponseEntity<Usuario> actualizarUsuario(@RequestBody Usuario usuario);
	
	@PostMapping("/accederUsuario")
	public ResponseEntity<String> accederUsuario(@RequestBody UsuarioContrasena signup);
	
	@GetMapping("/buscarPorCodUsuario/{codUsuario}")
	public ResponseEntity<Usuario> buscarPorCodUsuario(@PathVariable String codUsuario);
	
	@GetMapping("/welcome")
	public ResponseEntity<String> buscarUsuario();

}
