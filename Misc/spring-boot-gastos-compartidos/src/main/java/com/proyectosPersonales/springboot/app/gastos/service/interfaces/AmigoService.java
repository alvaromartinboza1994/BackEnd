package com.proyectosPersonales.springboot.app.gastos.service.interfaces;

import com.proyectosPersonales.springboot.app.gastos.dto.AmigoDTO;
import com.proyectosPersonales.springboot.app.gastos.dto.PersonaDTO;

public interface AmigoService {
	
	PersonaDTO buscarPorNombre(String nombre);
	
	void guardarAmigo(AmigoDTO amigo);

}
