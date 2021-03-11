package com.proyectosPersonales.springboot.app.gastos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyectosPersonales.springboot.app.gastos.dto.Pago;

@Repository
public interface PagoDaoI extends JpaRepository<Pago, Integer>{
	
	
}
