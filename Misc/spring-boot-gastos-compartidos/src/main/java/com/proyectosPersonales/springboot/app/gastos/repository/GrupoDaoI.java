package com.proyectosPersonales.springboot.app.gastos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyectosPersonales.springboot.app.gastos.dto.Grupo;
import com.proyectosPersonales.springboot.app.gastos.dto.GrupoPK;

@Repository
public interface GrupoDaoI extends JpaRepository<Grupo, GrupoPK>{
	
	Grupo findByIdNombreGrupo(String nombre);

}
