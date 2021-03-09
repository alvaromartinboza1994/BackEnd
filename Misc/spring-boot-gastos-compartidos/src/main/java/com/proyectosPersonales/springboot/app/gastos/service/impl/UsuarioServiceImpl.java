package com.proyectosPersonales.springboot.app.gastos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.proyectosPersonales.springboot.app.gastos.dto.Usuario;
import com.proyectosPersonales.springboot.app.gastos.repository.UsuarioDaoI;
import com.proyectosPersonales.springboot.app.gastos.service.interfaces.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioDaoI usuarioDao;

	@Override
	public Usuario guardarUsuario(Usuario usuario) {
		return usuarioDao.save(usuario);
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

}
