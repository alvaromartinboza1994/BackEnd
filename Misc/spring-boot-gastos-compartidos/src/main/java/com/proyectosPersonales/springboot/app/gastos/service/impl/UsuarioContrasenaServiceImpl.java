package com.proyectosPersonales.springboot.app.gastos.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectosPersonales.springboot.app.gastos.dto.UsuarioContrasena;
import com.proyectosPersonales.springboot.app.gastos.exception.ApiException;
import com.proyectosPersonales.springboot.app.gastos.repository.UsuarioContrasenaDaoI;
import com.proyectosPersonales.springboot.app.gastos.service.interfaces.UsuarioContrasenaService;

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
