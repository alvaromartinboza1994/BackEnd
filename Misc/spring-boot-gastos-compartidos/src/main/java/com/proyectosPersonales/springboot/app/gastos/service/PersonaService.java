package com.proyectosPersonales.springboot.app.gastos.service;

import com.proyectosPersonales.springboot.app.gastos.dto.PersonaDTO;

public interface PersonaService {
	
	PersonaDTO buscarPersonaPorId(Integer id);
	
	void guardarPersona(PersonaDTO persona);

}
