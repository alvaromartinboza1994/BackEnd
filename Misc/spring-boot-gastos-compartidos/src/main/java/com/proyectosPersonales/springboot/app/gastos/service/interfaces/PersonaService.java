package com.proyectosPersonales.springboot.app.gastos.service.interfaces;

import com.proyectosPersonales.springboot.app.gastos.dto.PersonaDTO;

public interface PersonaService {
	
	PersonaDTO buscarPorNombreYApellidos(String nombre, String apellidos);
	
	PersonaDTO buscarPorNombre(String nombre);
	
	void guardarPersona(PersonaDTO persona);

}
