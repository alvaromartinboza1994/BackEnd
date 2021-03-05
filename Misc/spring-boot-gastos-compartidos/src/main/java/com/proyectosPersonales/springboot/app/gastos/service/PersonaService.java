package com.proyectosPersonales.springboot.app.gastos.service;

import com.proyectosPersonales.springboot.app.gastos.dto.PersonaDTO;

public interface PersonaService {
	
	PersonaDTO buscarPersonaPorNombre(String nombre);
	
	void guardarPersona(PersonaDTO persona);

}
