package com.proyectosPersonales.springboot.app.gastos.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.proyectosPersonales.springboot.app.gastos.dto.Usuario;
import com.proyectosPersonales.springboot.app.gastos.dto.UsuarioContrasena;
import com.proyectosPersonales.springboot.app.gastos.exception.ApiException;
import com.proyectosPersonales.springboot.app.gastos.repository.UsuarioDaoI;
import com.proyectosPersonales.springboot.app.gastos.service.interfaces.UsuarioContrasenaService;
import com.proyectosPersonales.springboot.app.gastos.service.interfaces.UsuarioService;

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
	public Usuario buscarPorNombreYApellidos(String nombre, String apellidos) {
		try {
			return usuarioDao.findByNombreAndApellidos(nombre, apellidos);
		} catch (Exception e) {
			throw new ApiException("USER_NOT_FOUND", "No existe el usuario " + nombre + " " + apellidos);
		}
	}

	@Override
	@Transactional
	public Usuario buscarPorCodUsuario(String codUsuario) {
		try {
			return usuarioDao.findByCodUsuario(codUsuario);
		} catch (Exception e) {
			throw new ApiException("USER_NOT_FOUND", "No existe el usuario " + codUsuario);
		}
	}

	@Override
	@Transactional
	public Usuario buscarPorIdUsuario(Integer idUsuario) {
		try {
			return usuarioDao.findByIdUsuario(idUsuario);
		} catch (Exception e) {
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
			throw new ApiException("PASSWORD_INCORRECT", "La contraseña no coincide con la registrada en el sistema");
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
