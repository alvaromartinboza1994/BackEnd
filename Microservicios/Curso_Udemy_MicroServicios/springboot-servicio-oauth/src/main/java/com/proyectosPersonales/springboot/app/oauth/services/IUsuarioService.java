package com.proyectosPersonales.springboot.app.oauth.services;

import com.proyectosPersonales.springboot.app.usuarios.commons.models.entity.Usuario;

public interface IUsuarioService {
	
	public Usuario findByUsername(String username);

}
