package com.proyectosPersonales.springboot.app.gastos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyectosPersonales.springboot.app.gastos.dto.Grupo;
import com.proyectosPersonales.springboot.app.gastos.dto.GrupoPK;

public interface GrupoDaoI extends JpaRepository<Grupo, GrupoPK>{
	
	Grupo findByIdNombreGrupo(String nombre);

}
