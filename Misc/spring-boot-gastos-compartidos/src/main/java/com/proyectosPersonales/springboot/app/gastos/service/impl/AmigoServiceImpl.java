package com.proyectosPersonales.springboot.app.gastos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.proyectosPersonales.springboot.app.gastos.dto.AmigoDTO;
import com.proyectosPersonales.springboot.app.gastos.dto.PersonaDTO;
import com.proyectosPersonales.springboot.app.gastos.repository.AmigoDaoI;
import com.proyectosPersonales.springboot.app.gastos.service.interfaces.AmigoService;

public class AmigoServiceImpl implements AmigoService {
	
	@Autowired
	private AmigoDaoI amigoDao;

	@Override
	public PersonaDTO buscarPorNombre(String nombre) {
		return amigoDao.findByNombre(nombre);
	}

	@Override
	public void guardarAmigo(AmigoDTO amigo) {
		amigoDao.save(amigo);

	}

}
