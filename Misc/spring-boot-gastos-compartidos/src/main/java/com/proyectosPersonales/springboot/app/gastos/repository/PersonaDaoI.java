package com.proyectosPersonales.springboot.app.gastos.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.proyectosPersonales.springboot.app.gastos.dto.PersonaDTO;

@Repository
public interface PersonaDaoI extends CrudRepository<PersonaDTO, Integer>{
	
	public PersonaDTO findByNombre(String nombre);

	public PersonaDTO findByNombreAndApellidos(String nombre, String apellidos);

}
