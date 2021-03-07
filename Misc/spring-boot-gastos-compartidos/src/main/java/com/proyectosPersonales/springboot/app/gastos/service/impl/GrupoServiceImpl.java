
package com.proyectosPersonales.springboot.app.gastos.service.impl;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectosPersonales.springboot.app.gastos.dto.Amigo;
import com.proyectosPersonales.springboot.app.gastos.dto.Grupo;
import com.proyectosPersonales.springboot.app.gastos.dto.GrupoPK;
import com.proyectosPersonales.springboot.app.gastos.dto.Usuario;
import com.proyectosPersonales.springboot.app.gastos.dto.UsuarioAmigo;
import com.proyectosPersonales.springboot.app.gastos.repository.GrupoDaoI;
import com.proyectosPersonales.springboot.app.gastos.repository.UsuarioDaoI;
import com.proyectosPersonales.springboot.app.gastos.service.interfaces.AmigoService;
import com.proyectosPersonales.springboot.app.gastos.service.interfaces.GrupoService;
import com.proyectosPersonales.springboot.app.gastos.service.interfaces.UsuarioService;

import lombok.extern.slf4j.Slf4j;

@Service
public class GrupoServiceImpl implements GrupoService {

	@Autowired
	GrupoDaoI grupoDao;
	
	@Autowired
	UsuarioService usuarioService;

	@Override
	public void crearGrupo(String nombreGrupo, String codUsuario) {
		Usuario usuario_db = usuarioService.buscarPorCodUsuario(codUsuario);
		if(usuario_db != null) {
			Grupo miGrupo = Grupo.builder().id(GrupoPK.builder().nombreGrupo(nombreGrupo).idGrupo(grupoDao.findAll().size()+1).build()).participantes(Arrays.asList(usuario_db)).build();
			usuario_db.setMiGrupo(miGrupo.getId().getNombreGrupo());
			grupoDao.save(miGrupo);
			usuarioService.guardarUsuario(usuario_db);
		}
	}

	@Override
	public void añadirParticipante(String nombreGrupo, String codUsuario) {
		Usuario usuario_db = usuarioService.buscarPorCodUsuario(codUsuario);
		if(usuario_db != null) {
			Grupo grupo_db = grupoDao.findByIdNombreGrupo(nombreGrupo);
			if(grupo_db != null) {
				grupo_db.getParticipantes().add(usuario_db);
				usuario_db.setMiGrupo(grupo_db.getId().getNombreGrupo());
				grupoDao.save(grupo_db);
				usuarioService.guardarUsuario(usuario_db);
			}
		}
	}

	@Override
	public Grupo buscarGrupo(String nombreGrupo) {
		return grupoDao.findByIdNombreGrupo(nombreGrupo);
	}
}
