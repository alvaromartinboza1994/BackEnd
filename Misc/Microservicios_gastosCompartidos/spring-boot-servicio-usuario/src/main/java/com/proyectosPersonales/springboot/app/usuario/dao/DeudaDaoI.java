package com.proyectosPersonales.springboot.app.usuario.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyectosPersonales.springboot.app.commons.dto.Deuda;

public interface DeudaDaoI extends JpaRepository<Deuda, Integer>{
	
	Deuda findByIdDeuda(Integer idDeuda);
		
}
