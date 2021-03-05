package com.proyectosPersonales.springboot.app.gastos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.proyectosPersonales.springboot.app.gastos.dto.PersonaDTO;
import com.proyectosPersonales.springboot.app.gastos.dto.UsuarioDTO;
import com.proyectosPersonales.springboot.app.gastos.repository.UsuarioDaoI;
import com.proyectosPersonales.springboot.app.gastos.service.interfaces.UsuarioService;

public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
	private UsuarioDaoI usuarioDao;

	@Override
	public PersonaDTO buscarPorNombre(String nombre) {
		return usuarioDao.findByNombre(nombre);
	}

	@Override
	public void guardarUsuario(UsuarioDTO usuario) {
		usuarioDao.save(usuario);
		
	}

}
