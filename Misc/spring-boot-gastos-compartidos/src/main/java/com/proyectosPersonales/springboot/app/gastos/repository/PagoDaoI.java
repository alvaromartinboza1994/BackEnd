package com.proyectosPersonales.springboot.app.gastos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyectosPersonales.springboot.app.gastos.dto.Pago;

public interface PagoDaoI extends JpaRepository<Pago, Integer>{
	
	
}
