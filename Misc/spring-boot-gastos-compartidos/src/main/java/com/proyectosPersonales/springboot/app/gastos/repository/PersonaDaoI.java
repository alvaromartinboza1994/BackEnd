package com.proyectosPersonales.springboot.app.gastos.repository;

import org.springframework.data.repository.CrudRepository;

import com.proyectosPersonales.springboot.app.gastos.dto.PersonaDTO;

public interface PersonaDaoI extends CrudRepository<PersonaDTO, Integer>{

}
