package com.proyectosPersonales.springboot.app.gastos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyectosPersonales.springboot.app.gastos.dto.Usuario;
import com.proyectosPersonales.springboot.app.gastos.dto.UsuarioContrasena;

public interface UsuarioContrasenaDaoI extends JpaRepository<UsuarioContrasena, Integer>{
	
	UsuarioContrasena findByUsuarioCodUsuario(String codUsuario);

}
