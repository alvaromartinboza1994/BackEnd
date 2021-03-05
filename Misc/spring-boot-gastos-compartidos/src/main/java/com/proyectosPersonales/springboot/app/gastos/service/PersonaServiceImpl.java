package com.proyectosPersonales.springboot.app.gastos.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.proyectosPersonales.springboot.app.gastos.dto.PersonaDTO;
import com.proyectosPersonales.springboot.app.gastos.repository.PersonaDaoI;

public class PersonaServiceImpl implements PersonaService {
	
	@Autowired
	private PersonaDaoI personaDao;

	@Override
	public PersonaDTO buscarPersonaPorId(Integer id) {
		return personaDao.findById(id).get();
	}

	@Override
	public void guardarPersona(PersonaDTO persona) {
		personaDao.save(persona);
	}
	
	

}
