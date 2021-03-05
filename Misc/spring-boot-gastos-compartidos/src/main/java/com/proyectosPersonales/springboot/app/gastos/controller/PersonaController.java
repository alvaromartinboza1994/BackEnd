package com.proyectosPersonales.springboot.app.gastos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyectosPersonales.springboot.app.gastos.dto.PersonaDTO;
import com.proyectosPersonales.springboot.app.gastos.service.PersonaService;

@RestController
@RequestMapping("persona")
public class PersonaController {

	@Autowired
	private PersonaService personaService;

	@PostMapping("/guardarPersona")
	public void guardarPersona(@RequestBody PersonaDTO persona) {
		personaService.guardarPersona(persona);
	}

	@GetMapping("/buscarPersonaPorId")
	public PersonaDTO guardarPersona(@RequestBody Integer id) {
		return personaService.buscarPersonaPorId(id);
	}

}
