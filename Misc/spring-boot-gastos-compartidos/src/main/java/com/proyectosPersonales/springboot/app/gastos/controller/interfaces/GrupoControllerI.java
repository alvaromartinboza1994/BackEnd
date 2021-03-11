
package com.proyectosPersonales.springboot.app.gastos.controller.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.proyectosPersonales.springboot.app.gastos.dto.Grupo;

public interface GrupoControllerI {
	
	@PostMapping("/crearGrupo/{nombre}/usuario/{codUsuario}")
	public ResponseEntity<Grupo> crearGrupo(@PathVariable String nombre, @PathVariable String codUsuario) throws Exception;
	
	@PostMapping("/anadirParticipante/{nombreGrupo}/usuario/{codUsuario}")
	public ResponseEntity<Grupo> añadirParticipante(@PathVariable String nombreGrupo, @PathVariable String codUsuario);
	
	@GetMapping("/buscarGrupo/{nombreGrupo}")
	public ResponseEntity<Grupo> buscarGrupo(@PathVariable String nombreGrupo);

}
