package com.proyectosPersonales.springboot.app.gastos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.proyectosPersonales.springboot.app.gastos.dto.PersonaDTO;
import com.proyectosPersonales.springboot.app.gastos.repository.PersonaDaoI;

@Service
public class PersonaServiceImpl implements PersonaService {

	@Autowired
	private PersonaDaoI personaDao;

	@Override
	public void guardarPersona(PersonaDTO persona) {
		personaDao.save(persona);
	}

	@Override
	public PersonaDTO buscarPersonaPorNombre(String nombre) {
		return personaDao.findByNombre(nombre);
	}

}
