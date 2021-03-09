package com.proyectosPersonales.springboot.app.gastos.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyectosPersonales.springboot.app.gastos.dto.UsuarioContrasena;
import com.proyectosPersonales.springboot.app.gastos.controller.interfaces.UsuarioControllerI;
import com.proyectosPersonales.springboot.app.gastos.dto.Pago;
import com.proyectosPersonales.springboot.app.gastos.dto.Usuario;
import com.proyectosPersonales.springboot.app.gastos.dto.UsuarioPago;
import com.proyectosPersonales.springboot.app.gastos.service.interfaces.UsuarioService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("usuario")
@Slf4j
public class UsuarioControllerImpl implements UsuarioControllerI{

	@Autowired
	private UsuarioService usuarioService;
	
	@Override
	public ResponseEntity<Usuario> registrarUsuario(@RequestBody UsuarioContrasena signup) {
		return new ResponseEntity<>(usuarioService.guardarUsuario(signup), HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<String> accederUsuario(@RequestBody UsuarioContrasena signup) {
		return usuarioService.accederUsuario(signup);
	}

	@Override
	public ResponseEntity<Usuario> buscarUsuario(@RequestBody Usuario usuario) {
		return new ResponseEntity<>(usuarioService.buscarPorNombreYApellidos(usuario.getNombre(), usuario.getApellidos()), HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<Usuario> buscarUsuario(@PathVariable String codUsuario) {
		return new ResponseEntity<>(usuarioService.buscarPorCodUsuario(codUsuario), HttpStatus.OK);
	}

}
