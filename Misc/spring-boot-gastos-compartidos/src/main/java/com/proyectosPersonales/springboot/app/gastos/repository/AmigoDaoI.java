package com.proyectosPersonales.springboot.app.gastos.repository;

import org.springframework.data.repository.CrudRepository;

import com.proyectosPersonales.springboot.app.gastos.dto.AmigoDTO;
import com.proyectosPersonales.springboot.app.gastos.dto.PersonaDTO;

public interface AmigoDaoI extends CrudRepository<AmigoDTO, Integer>{

}
