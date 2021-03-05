package com.proyectosPersonales.springboot.app.gastos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.proyectosPersonales.springboot.app.gastos.dto.PersonaDTO;
import com.proyectosPersonales.springboot.app.gastos.repository.PersonaDaoI;
import com.proyectosPersonales.springboot.app.gastos.service.interfaces.PersonaService;

@Service
public class PersonaServiceImpl implements PersonaService {

	@Autowired
	private PersonaDaoI personaDao;

	@Override
	public void guardarPersona(PersonaDTO persona) {
		personaDao.save(persona);
	}

	@Override
	public PersonaDTO buscarPorNombreYApellidos(String nombre, String apellidos) {
		return personaDao.findByNombreAndApellidos(nombre, apellidos);
	}

	@Override
	public PersonaDTO buscarPorNombre(String nombre) {
		return personaDao.findByNombre(nombre);
	}

}
