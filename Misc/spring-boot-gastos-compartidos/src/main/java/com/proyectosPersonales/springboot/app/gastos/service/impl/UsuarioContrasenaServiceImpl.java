package com.proyectosPersonales.springboot.app.gastos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.proyectosPersonales.springboot.app.gastos.dto.UsuarioContrasena;
import com.proyectosPersonales.springboot.app.gastos.repository.UsuarioContrasenaDaoI;
import com.proyectosPersonales.springboot.app.gastos.service.interfaces.UsuarioContrasenaService;
import com.proyectosPersonales.springboot.app.gastos.service.interfaces.UsuarioService;

@Service
public class UsuarioContrasenaServiceImpl implements UsuarioContrasenaService {

	@Autowired
	private UsuarioContrasenaDaoI usuarioContrasenaDao;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Override
	public UsuarioContrasena guardarUsuarioContrasena(UsuarioContrasena signup) {
		return usuarioContrasenaDao.save(signup);
	}

	@Override
	public UsuarioContrasena buscarPorUsuarioCodUsuario(String codUsuario) {
		UsuarioContrasena usuarioContrasena = usuarioContrasenaDao.findByUsuarioCodUsuario(codUsuario);
		if(usuarioContrasena != null) {
			return usuarioContrasena;
		}
		throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "No existe el usuario " + codUsuario);
	}

}
