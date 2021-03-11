
package com.proyectosPersonales.springboot.app.gastos.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyectosPersonales.springboot.app.gastos.controller.interfaces.GrupoControllerI;
import com.proyectosPersonales.springboot.app.gastos.dto.Grupo;
import com.proyectosPersonales.springboot.app.gastos.service.interfaces.GrupoService;

@RestController
@RequestMapping("grupo")
public class GrupoControllerImpl implements GrupoControllerI{

	@Autowired
	private GrupoService grupoService;
	
	@Override
	public ResponseEntity<Grupo> crearGrupo(String nombre, String codUsuario) throws Exception {
		return new ResponseEntity<>(grupoService.crearGrupo(nombre, codUsuario), HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<Grupo> añadirParticipante(String nombreGrupo, String codUsuario) {
		return new ResponseEntity<>(grupoService.añadirParticipante(nombreGrupo, codUsuario), HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<Grupo> buscarGrupo(String nombreGrupo) {
		return new ResponseEntity<>(grupoService.buscarGrupo(nombreGrupo), HttpStatus.OK);
	}

}
