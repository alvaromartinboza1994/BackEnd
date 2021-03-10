package com.proyectosPersonales.springboot.app.usuario.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectosPersonales.springboot.app.commons.dto.UsuarioContrasena;
import com.proyectosPersonales.springboot.app.commons.exception.ApiException;
import com.proyectosPersonales.springboot.app.usuario.dao.UsuarioContrasenaDaoI;
import com.proyectosPersonales.springboot.app.usuario.packages.service.interfaces.UsuarioContrasenaService;

@Service
public class UsuarioContrasenaServiceImpl implements UsuarioContrasenaService {

	@Autowired
	private UsuarioContrasenaDaoI usuarioContrasenaDao;
		
	@Override
	@Transactional
	public UsuarioContrasena guardarUsuarioContrasena(UsuarioContrasena signup) {
		try {
			return usuarioContrasenaDao.save(signup);
		} catch (Exception e) {
			throw new ApiException("PERSISTENCE_ERROR", "No se ha podido guardar el usuario " + signup.getUsuario().getCodUsuario() + " con contraseña " + signup.getContraseña());
		}
		
	}

	@Override
	@Transactional
	public UsuarioContrasena buscarPorUsuarioCodUsuario(String codUsuario) {
		UsuarioContrasena usuarioContrasena = usuarioContrasenaDao.findByUsuarioCodUsuario(codUsuario);
		if(usuarioContrasena != null) {
			return usuarioContrasena;
		} else {
			throw new ApiException("USER_NOT_FOUND", "No existe el usuario " + codUsuario);
		}
	}

}
