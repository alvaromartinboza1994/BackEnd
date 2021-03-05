package com.proyectosPersonales.springboot.app.gastos.repository;

import org.springframework.data.repository.CrudRepository;

import com.proyectosPersonales.springboot.app.gastos.dto.PagoDTO;
import com.proyectosPersonales.springboot.app.gastos.dto.PersonaDTO;

public interface PagoDaoI extends CrudRepository<PagoDTO, Integer>{
	
	public PagoDTO findByPagador(PersonaDTO pagador);

}
