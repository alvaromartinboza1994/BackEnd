package com.proyectosPersonales.springboot.app.gastos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.proyectosPersonales.springboot.app.gastos.dto.Pago;
import com.proyectosPersonales.springboot.app.gastos.dto.Usuario;
import com.proyectosPersonales.springboot.app.gastos.dto.UsuarioPago;
import com.proyectosPersonales.springboot.app.gastos.repository.UsuarioDaoI;
import com.proyectosPersonales.springboot.app.gastos.service.interfaces.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioDaoI usuarioRepository;

	@Override
	public void guardarUsuario(Usuario usuario) {
		usuarioRepository.save(usuario);
	}

	@Override
	public Usuario buscarPorNombreYApellidos(String nombre, String apellidos) {
		Usuario usuario_db = usuarioRepository.findByNombreAndApellidos(nombre, apellidos);
		if(usuario_db != null) {
			return usuario_db;
		}
		throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "No existe un usuario registrado con ese nombre y apellidos.");
	}

}
