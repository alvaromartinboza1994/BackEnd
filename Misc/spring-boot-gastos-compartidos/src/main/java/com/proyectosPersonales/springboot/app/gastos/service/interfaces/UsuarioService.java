package com.proyectosPersonales.springboot.app.gastos.service.interfaces;

import com.proyectosPersonales.springboot.app.gastos.dto.PersonaDTO;
import com.proyectosPersonales.springboot.app.gastos.dto.UsuarioDTO;

public interface UsuarioService {
	
	UsuarioDTO buscarPorId(Integer id);
	
	void guardarUsuario(UsuarioDTO usuario);

}
