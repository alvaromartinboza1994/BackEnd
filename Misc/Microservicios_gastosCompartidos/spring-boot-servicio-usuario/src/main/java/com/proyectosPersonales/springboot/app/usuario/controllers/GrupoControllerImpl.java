package com.proyectosPersonales.springboot.app.usuario.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyectosPersonales.springboot.app.commons.dto.Grupo;
import com.proyectosPersonales.springboot.app.usuario.packages.service.interfaces.GrupoService;

@RestController
@RequestMapping("grupo")
public class GrupoControllerImpl implements GrupoControllerI{

	@Autowired
	private GrupoService grupoService;
	
	@Override
	public ResponseEntity<Grupo> crearGrupo(@PathVariable String nombre, @PathVariable String codUsuario) throws Exception {
		return new ResponseEntity<>(grupoService.crearGrupo(nombre, codUsuario), HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<Grupo> añadirParticipante(@PathVariable String nombreGrupo, @PathVariable String codUsuario) {
		return new ResponseEntity<>(grupoService.añadirParticipante(nombreGrupo, codUsuario), HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<Grupo> buscarGrupo(@PathVariable String nombreGrupo) {
		return new ResponseEntity<>(grupoService.buscarGrupo(nombreGrupo), HttpStatus.OK);
	}

}
