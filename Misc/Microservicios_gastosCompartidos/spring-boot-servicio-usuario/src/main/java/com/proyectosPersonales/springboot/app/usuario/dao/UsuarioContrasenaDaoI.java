package com.proyectosPersonales.springboot.app.usuario.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyectosPersonales.springboot.app.commons.dto.Usuario;
import com.proyectosPersonales.springboot.app.commons.dto.UsuarioContrasena;

public interface UsuarioContrasenaDaoI extends JpaRepository<UsuarioContrasena, Integer>{
	
	UsuarioContrasena findByUsuarioCodUsuario(String codUsuario);

}
