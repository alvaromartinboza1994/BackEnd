package com.proyectosPersonales.springboot.app.gastos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyectosPersonales.springboot.app.gastos.dto.Deuda;

public interface DeudaDaoI extends JpaRepository<Deuda, Integer>{
	
	Deuda findByIdDeuda(Integer idDeuda);
		
}
