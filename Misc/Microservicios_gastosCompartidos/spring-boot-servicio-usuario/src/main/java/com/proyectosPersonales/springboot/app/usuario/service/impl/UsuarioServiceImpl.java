package com.proyectosPersonales.springboot.app.usuario.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.proyectosPersonales.springboot.app.commons.dto.Usuario;
import com.proyectosPersonales.springboot.app.commons.dto.UsuarioContrasena;
import com.proyectosPersonales.springboot.app.commons.exception.ApiException;
import com.proyectosPersonales.springboot.app.usuario.dao.UsuarioDaoI;
import com.proyectosPersonales.springboot.app.usuario.packages.service.interfaces.UsuarioContrasenaService;
import com.proyectosPersonales.springboot.app.usuario.packages.service.interfaces.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioDaoI usuarioDao;
	
	@Autowired
	private UsuarioContrasenaService usuarioContrasenaService;

	@Override
	@Transactional
	public Usuario guardarUsuario(UsuarioContrasena signup) {
		try {
			usuarioDao.save(signup.getUsuario());
			UsuarioContrasena usuarioContrasena = usuarioContrasenaService.guardarUsuarioContrasena(signup);
			return usuarioContrasena.getUsuario();
		} catch (Exception e) {
			throw new ApiException("PERSISTENCE_ERROR", "No se ha podido guardar el usuario " + signup.toString());
		}
	}

	@Override
	@Transactional
	public Usuario buscarPorCodUsuario(String codUsuario) {
		Usuario usuario_db = usuarioDao.findByCodUsuario(codUsuario);
		if(usuario_db != null) {
			return usuario_db;
		} else {
			throw new ApiException("USER_NOT_FOUND", "No existe el usuario " + codUsuario);
		}
	}

	@Override
	@Transactional
	public Usuario buscarPorIdUsuario(Integer idUsuario) {
		Usuario usuario_db = usuarioDao.findByIdUsuario(idUsuario);
		if(usuario_db != null) {
			return usuario_db;
		} else {
			throw new ApiException("USER_NOT_FOUND", "No existe el usuario con Id: " + idUsuario);
		}
	}

	@Override
	@Transactional
	public ResponseEntity<String> accederUsuario(UsuarioContrasena signup) {
		UsuarioContrasena usuarioContrasena = usuarioContrasenaService.buscarPorUsuarioCodUsuario(signup.getUsuario().getCodUsuario());
		if(signup.getContraseña().equals(usuarioContrasena.getContraseña())) {
			return new ResponseEntity<>("Acceso realizado con éxito!", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("La contraseña no coincide con la registrada en el sistema,", HttpStatus.FORBIDDEN);
		}
	}

	@Override
	@Transactional
	public Usuario actualizarUsuario(Usuario usuario) {
		try {
			return usuarioDao.save(usuario);
		} catch (Exception e) {
			throw new ApiException("USER_UPDATE", "No se ha podido actualizar al usuario " + usuario.toString());
		}
	}

}
