package com.proyectosPersonales.springboot.app.usuario.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyectosPersonales.springboot.app.commons.dto.Grupo;
import com.proyectosPersonales.springboot.app.commons.dto.GrupoPK;

public interface GrupoDaoI extends JpaRepository<Grupo, GrupoPK>{
	
	Grupo findByIdNombreGrupo(String nombre);

}
