package com.proyectosPersonales.springboot.app.gastos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyectosPersonales.springboot.app.gastos.dto.Pago;
import com.proyectosPersonales.springboot.app.gastos.dto.Usuario;
import com.proyectosPersonales.springboot.app.gastos.dto.UsuarioPago;
import com.proyectosPersonales.springboot.app.gastos.service.interfaces.UsuarioService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("usuario")
@Slf4j
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping("/registrarUsuario")
	public void guardarPersona(@RequestBody Usuario usuario) {
		usuarioService.guardarUsuario(usuario);
	}

	@GetMapping("/buscarUsuario")
	public Usuario buscarUsuario(@RequestBody Usuario usuario) {
		return usuarioService.buscarPorNombreYApellidos(usuario.getNombre(), usuario.getApellidos());
	}

}
