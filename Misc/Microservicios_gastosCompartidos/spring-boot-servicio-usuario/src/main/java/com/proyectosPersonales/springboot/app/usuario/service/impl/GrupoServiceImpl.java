
package com.proyectosPersonales.springboot.app.usuario.service.impl;

import java.util.Arrays;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectosPersonales.springboot.app.commons.dto.Grupo;
import com.proyectosPersonales.springboot.app.commons.dto.Usuario;
import com.proyectosPersonales.springboot.app.commons.dto.GrupoPK;
import com.proyectosPersonales.springboot.app.commons.exception.ApiException;
import com.proyectosPersonales.springboot.app.usuario.dao.GrupoDaoI;
import com.proyectosPersonales.springboot.app.usuario.packages.service.interfaces.GrupoService;
import com.proyectosPersonales.springboot.app.usuario.packages.service.interfaces.UsuarioService;

@Service
public class GrupoServiceImpl implements GrupoService {

	@Autowired
	GrupoDaoI grupoDao;
	
	@Autowired
	UsuarioService usuarioService;

	@Override
	@Transactional
	public Grupo crearGrupo(String nombreGrupo, String codUsuario) throws Exception {
		Usuario usuario_db = usuarioService.buscarPorCodUsuario(codUsuario);
		Grupo miGrupo = Grupo.builder().build();
		if(usuario_db != null) {
			miGrupo = Grupo.builder().id(GrupoPK.builder().nombreGrupo(nombreGrupo).idGrupo(grupoDao.findAll().size()+1).build()).participantes(Arrays.asList(usuario_db)).build();
			usuario_db.setMiGrupo(miGrupo.getId().getNombreGrupo());
			usuarioService.actualizarUsuario(usuario_db);
		}
		try {
			return grupoDao.save(miGrupo);
		} catch (Exception e) {
			throw new ApiException("PERSISTENCE_ERROR", "No se ha podido guardar el grupo " + miGrupo.toString());
		}
	}

	@Override
	@Transactional
	public Grupo añadirParticipante(String nombreGrupo, String codUsuario) {
		Usuario usuario_db = usuarioService.buscarPorCodUsuario(codUsuario);
		Grupo grupo_db = Grupo.builder().build();
		if(usuario_db != null) {
			grupo_db = buscarGrupo(nombreGrupo);
			if(grupo_db != null) {
				grupo_db.getParticipantes().add(usuario_db);
				usuario_db.setMiGrupo(grupo_db.getId().getNombreGrupo());
				usuarioService.actualizarUsuario(usuario_db);
			}			
		}
		try {
			return grupoDao.save(grupo_db);
		} catch (Exception e) {
			throw new ApiException("PERSISTENCE_ERROR", "No se ha podido guardar el grupo " + grupo_db.toString());
		}
		
	}

	@Override
	@Transactional
	public Grupo buscarGrupo(String nombreGrupo) {
		Grupo grupo = grupoDao.findByIdNombreGrupo(nombreGrupo);
		if(grupo != null) {
			return grupo;
		} else {
			throw new ApiException("GRUPO_NOT_FOUND", "No se ha encontrado el grupo " + nombreGrupo);
		}
	}
}
