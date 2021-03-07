
package com.proyectosPersonales.springboot.app.gastos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectosPersonales.springboot.app.gastos.dto.Amigo;
import com.proyectosPersonales.springboot.app.gastos.dto.Usuario;
import com.proyectosPersonales.springboot.app.gastos.dto.UsuarioAmigo;
import com.proyectosPersonales.springboot.app.gastos.repository.UsuarioDaoI;
import com.proyectosPersonales.springboot.app.gastos.service.interfaces.AmigoService;
import com.proyectosPersonales.springboot.app.gastos.service.interfaces.GrupoService;
import com.proyectosPersonales.springboot.app.gastos.service.interfaces.UsuarioService;

@Service
public class AmigoServiceImpl implements AmigoService {

	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	GrupoService grupoService;

	@Override
	public void añadirUsuarioAmigo(UsuarioAmigo usuarioAmigo) {
		Usuario amigo_db = usuarioService.buscarPorCodUsuario(usuarioAmigo.getAmigo().getCodAmigo());
		if(amigo_db != null) {
			Usuario usuario_db = usuarioService.buscarPorCodUsuario(usuarioAmigo.getUsuario().getCodUsuario());
			if(usuario_db != null) {
				usuario_db.getMisAmigos().add(usuarioAmigo.getAmigo());
				amigo_db.getMisAmigos().add(Amigo.builder()
						.codAmigo(usuario_db.getCodUsuario())
						.build());
				usuarioService.guardarUsuario(usuario_db);
				usuarioService.guardarUsuario(amigo_db);
			}
		}
	}
}
