package com.proyectosPersonales.springboot.app.gastos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyectosPersonales.springboot.app.gastos.dto.Pago;
import com.proyectosPersonales.springboot.app.gastos.dto.Usuario;

public interface PagoDaoI extends JpaRepository<Pago, Integer>{
	
	
}
