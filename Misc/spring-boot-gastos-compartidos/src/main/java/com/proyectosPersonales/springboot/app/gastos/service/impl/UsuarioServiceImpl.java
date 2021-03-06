package com.proyectosPersonales.springboot.app.gastos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectosPersonales.springboot.app.gastos.dto.Usuario;
import com.proyectosPersonales.springboot.app.gastos.dto.UsuarioPk;
import com.proyectosPersonales.springboot.app.gastos.repository.UsuarioDaoI;
import com.proyectosPersonales.springboot.app.gastos.service.interfaces.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioDaoI usuarioDao;

	@Override
	public void guardarUsuario(Usuario usuario) {
		UsuarioPk usuarioPk = usuario.getId();
		usuarioPk.setIdUsuario(usuarioDao.findAll().size() + 1);
		usuario.setId(usuarioPk);
		usuarioDao.save(usuario);
	}

	@Override
	public Usuario buscarPorNombreYApellidos(String nombre, String apellidos) {
		return usuarioDao.findByIdNombreAndIdApellidos(nombre, apellidos);
	}

}
