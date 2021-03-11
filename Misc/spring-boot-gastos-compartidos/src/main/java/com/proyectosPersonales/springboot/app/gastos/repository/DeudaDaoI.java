package com.proyectosPersonales.springboot.app.gastos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyectosPersonales.springboot.app.gastos.dto.Deuda;

@Repository
public interface DeudaDaoI extends JpaRepository<Deuda, Integer>{
	
	Deuda findByIdDeuda(Integer idDeuda);
		
}
