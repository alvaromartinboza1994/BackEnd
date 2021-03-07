package com.proyectosPersonales.springboot.app.gastos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyectosPersonales.springboot.app.gastos.dto.Deuda;

public interface DeudaDaoI extends JpaRepository<Deuda, Integer>{
	
	List<Deuda> findByIdPagador(Integer idPagador);
	
	
}
