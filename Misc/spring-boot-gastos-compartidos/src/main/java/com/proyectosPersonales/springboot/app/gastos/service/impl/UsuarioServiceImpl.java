package com.proyectosPersonales.springboot.app.gastos.service.impl;

import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.proyectosPersonales.springboot.app.gastos.dto.UsuarioContrasena;
import com.proyectosPersonales.springboot.app.gastos.dto.Usuario;
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
	public Usuario guardarUsuario(UsuarioContrasena signup) {
		usuarioDao.save(signup.getUsuario());
		UsuarioContrasena usuarioContrasena = usuarioContrasenaService.guardarUsuarioContrasena(signup);
		return usuarioContrasena.getUsuario();
	}

	@Override
	public Usuario buscarPorNombreYApellidos(String nombre, String apellidos) {
		Usuario usuario_db = usuarioDao.findByNombreAndApellidos(nombre, apellidos);
		if(usuario_db != null) {
			return usuario_db;
		}
		throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "No existe el usuario " + nombre + " " + apellidos);
	}

	@Override
	public Usuario buscarPorCodUsuario(String codUsuario) {
		Usuario usuario_db = usuarioDao.findByCodUsuario(codUsuario);
		if(usuario_db != null) {
			return usuario_db;
		}
		throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "No existe el usuario " + codUsuario);
	}

	@Override
	public Usuario buscarPorIdUsuario(Integer idUsuario) {
		Usuario usuario_db = usuarioDao.findByIdUsuario(idUsuario);
		if(usuario_db != null) {
			return usuario_db;
		}
		throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "No existe el usuario con Id " + idUsuario);
	}

	@Override
	public ResponseEntity<String> accederUsuario(UsuarioContrasena signup) {
		UsuarioContrasena usuarioContrasena = usuarioContrasenaService.buscarPorUsuarioCodUsuario(signup.getUsuario().getCodUsuario());
		if(signup.getContraseña().equals(usuarioContrasena.getContraseña())) {
			return new ResponseEntity<>("Acceso realizado con éxito!", HttpStatus.OK);
		}
		return new ResponseEntity<>("La contraseña no coincide con la registrada en el sistema", HttpStatus.FORBIDDEN);
	}

	@Override
	public Usuario actualizarUsuario(Usuario usuario) {
		return usuarioDao.save(usuario);
	}

}
