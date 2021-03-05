package com.proyectosPersonales.springboot.app.gastos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyectosPersonales.springboot.app.gastos.dto.PersonaDTO;
import com.proyectosPersonales.springboot.app.gastos.dto.UsuarioDTO;
import com.proyectosPersonales.springboot.app.gastos.service.interfaces.PersonaService;
import com.proyectosPersonales.springboot.app.gastos.service.interfaces.UsuarioService;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private PersonaService personaService;

	@PostMapping("/guardarUsuario")
	public void guardarPersona(@RequestBody UsuarioDTO usuario) {
		usuarioService.guardarUsuario(usuario);
	}

	@GetMapping("/buscarUsuario")
	public PersonaDTO buscarPersonaPorNombre(@RequestBody PersonaDTO persona) {
		PersonaDTO persona_db = personaService.buscarPorNombreYApellidos(persona.getNombre(), persona.getApellidos());
		return usuarioService.buscarPorId(persona_db.getIdPersona());
	}

}
