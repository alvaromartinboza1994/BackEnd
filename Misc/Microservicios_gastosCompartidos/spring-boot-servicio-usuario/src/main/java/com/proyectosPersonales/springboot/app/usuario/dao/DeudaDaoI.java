package com.proyectosPersonales.springboot.app.usuario.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyectosPersonales.springboot.app.commons.dto.Deuda;

@Repository
public interface DeudaDaoI extends JpaRepository<Deuda, Integer>{
	
	Deuda findByIdDeuda(Integer idDeuda);
		
}
