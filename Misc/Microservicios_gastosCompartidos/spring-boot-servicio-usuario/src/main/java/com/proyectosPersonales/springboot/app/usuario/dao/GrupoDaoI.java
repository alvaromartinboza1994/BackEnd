package com.proyectosPersonales.springboot.app.usuario.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyectosPersonales.springboot.app.commons.dto.Grupo;
import com.proyectosPersonales.springboot.app.commons.dto.GrupoPK;

@Repository
public interface GrupoDaoI extends JpaRepository<Grupo, GrupoPK>{
	
	Grupo findByIdNombreGrupo(String nombre);

}
