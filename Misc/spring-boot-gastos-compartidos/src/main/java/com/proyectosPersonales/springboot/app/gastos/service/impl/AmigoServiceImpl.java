
package com.proyectosPersonales.springboot.app.gastos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectosPersonales.springboot.app.gastos.dto.Usuario;
import com.proyectosPersonales.springboot.app.gastos.dto.UsuarioAmigo;
import com.proyectosPersonales.springboot.app.gastos.repository.UsuarioDaoI;
import com.proyectosPersonales.springboot.app.gastos.service.interfaces.AmigoService;
import com.proyectosPersonales.springboot.app.gastos.service.interfaces.UsuarioService;

@Service
public class AmigoServiceImpl implements AmigoService {

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	private UsuarioDaoI usuarioDao;

	@Override
	public void añadirUsuarioAmigo(UsuarioAmigo usuarioAmigo) {
		Usuario amigo = usuarioService.buscarPorNombreYApellidos(usuarioAmigo.getAmigo().getNombre(),
				usuarioAmigo.getAmigo().getApellidos());
		if(amigo != null) {
			Usuario usuario = usuarioService.buscarPorNombreYApellidos(usuarioAmigo.getUsuario().getNombre(),
					usuarioAmigo.getUsuario().getApellidos());
			usuario.getMisAmigos().add(usuarioAmigo.getAmigo());
			usuarioDao.save(usuario);
		}
	}
}
