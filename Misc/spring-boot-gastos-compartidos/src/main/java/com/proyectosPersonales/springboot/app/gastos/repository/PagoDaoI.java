package com.proyectosPersonales.springboot.app.gastos.repository;

import org.springframework.data.repository.CrudRepository;

import com.proyectosPersonales.springboot.app.gastos.dto.PagoDTO;

public interface PagoDaoI extends CrudRepository<PagoDTO, Integer>{

}
