
package com.proyectosPersonales.springboot.app.gastos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyectosPersonales.springboot.app.gastos.dto.Grupo;
import com.proyectosPersonales.springboot.app.gastos.dto.Usuario;
import com.proyectosPersonales.springboot.app.gastos.service.interfaces.GrupoService;

@RestController
@RequestMapping("grupo")
public class GrupoController {

	@Autowired
	private GrupoService grupoService;
	
	@PostMapping("/crearGrupo/{nombre}/usuario/{codUsuario}")
	public Grupo crearGrupo(@PathVariable String nombre, @PathVariable String codUsuario) throws Exception {
		return grupoService.crearGrupo(nombre, codUsuario);
	}
	
	@PostMapping("/anadirParticipante/{nombreGrupo}/usuario/{codUsuario}")
	public Grupo añadirParticipante(@PathVariable String nombreGrupo, @PathVariable String codUsuario) {
		return grupoService.añadirParticipante(nombreGrupo, codUsuario);
	}
	
	@GetMapping("/consultarGrupo/{nombreGrupo}")
	public Grupo consultarGrupo(@PathVariable String nombreGrupo) {
		return grupoService.buscarGrupo(nombreGrupo);
	}

}
