
package com.proyectosPersonales.springboot.app.gastos.service.impl;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.proyectosPersonales.springboot.app.gastos.dto.Grupo;
import com.proyectosPersonales.springboot.app.gastos.dto.GrupoPK;
import com.proyectosPersonales.springboot.app.gastos.dto.Usuario;
import com.proyectosPersonales.springboot.app.gastos.repository.GrupoDaoI;
import com.proyectosPersonales.springboot.app.gastos.service.interfaces.GrupoService;
import com.proyectosPersonales.springboot.app.gastos.service.interfaces.UsuarioService;

@Service
public class GrupoServiceImpl implements GrupoService {

	@Autowired
	GrupoDaoI grupoDao;
	
	@Autowired
	UsuarioService usuarioService;

	@Override
	public Grupo crearGrupo(String nombreGrupo, String codUsuario) throws Exception {
		Usuario usuario_db = usuarioService.buscarPorCodUsuario(codUsuario);
		Grupo miGrupo = Grupo.builder().build();
		if(usuario_db != null) {
			miGrupo = Grupo.builder().id(GrupoPK.builder().nombreGrupo(nombreGrupo).idGrupo(grupoDao.findAll().size()+1).build()).participantes(Arrays.asList(usuario_db)).build();
			usuario_db.setMiGrupo(miGrupo.getId().getNombreGrupo());
			usuarioService.actualizarUsuario(usuario_db);
		}
		return grupoDao.save(miGrupo);
	}

	@Override
	public Grupo añadirParticipante(String nombreGrupo, String codUsuario) {
		Usuario usuario_db = usuarioService.buscarPorCodUsuario(codUsuario);
		Grupo grupo_db = Grupo.builder().build();
		if(usuario_db != null) {
			grupo_db = grupoDao.findByIdNombreGrupo(nombreGrupo);
			if(grupo_db != null) {
				grupo_db.getParticipantes().add(usuario_db);
				usuario_db.setMiGrupo(grupo_db.getId().getNombreGrupo());
				usuarioService.actualizarUsuario(usuario_db);
			}
		}
		return grupoDao.save(grupo_db);
	}

	@Override
	public Grupo buscarGrupo(String nombreGrupo) {
		Grupo grupo_db = grupoDao.findByIdNombreGrupo(nombreGrupo);
		if(grupo_db != null) {
			return grupo_db;
		}
		throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "No existe el grupo " + nombreGrupo);
	}
}
