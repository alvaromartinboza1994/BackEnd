package com.proyectosPersonales.springboot.app.usuario.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyectosPersonales.springboot.app.commons.dto.UsuarioContrasena;

@Repository
public interface UsuarioContrasenaDaoI extends JpaRepository<UsuarioContrasena, Integer>{
	
	UsuarioContrasena findByUsuarioCodUsuario(String codUsuario);

}
