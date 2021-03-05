package com.proyectosPersonales.springboot.app.gastos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyectosPersonales.springboot.app.gastos.dto.PersonaDTO;
import com.proyectosPersonales.springboot.app.gastos.service.interfaces.PersonaService;

@RestController
@RequestMapping("persona")
public class PagoController {

	@Autowired
	private PersonaService personaService;

	@PostMapping("/guardarPersona")
	public void guardarPersona(@RequestBody PersonaDTO persona) {
		personaService.guardarPersona(persona);
	}

	@GetMapping("/buscarPersonaPorNombre/{nombre}")
	public PersonaDTO buscarPersonaPorNombre(@PathVariable String nombre) {
		return personaService.buscarPorNombre(nombre);
	}

}
