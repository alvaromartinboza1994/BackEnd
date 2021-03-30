package com.proyectosPersonales.springboot.app.pago.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.proyectosPersonales.springboot.app.commons.dto.Balance;
import com.proyectosPersonales.springboot.app.commons.dto.Grupo;
import com.proyectosPersonales.springboot.app.commons.dto.Usuario;
import com.proyectosPersonales.springboot.app.commons.dto.UsuarioActualizar;
import com.proyectosPersonales.springboot.app.commons.dto.UsuarioContrasena;


@FeignClient(name = "servicio-usuario") 
public interface UsuarioClienteRest {

	//Usuario
	
	@PostMapping("/usuario/guardarUsuario")
	public ResponseEntity<Usuario> guardarUsuario(@RequestBody UsuarioContrasena signup);
	
	@PutMapping("/usuario/actualizarUsuario")
	public ResponseEntity<Usuario> actualizarUsuario(@RequestBody UsuarioActualizar usuario);
	
	@GetMapping("/usuario/accederUsuario/{codUsuario}/contrasena/{contrasena}")
	public ResponseEntity<String> accederUsuario(@PathVariable String codUsuario, @PathVariable String contrasena);
	
	@GetMapping("/usuario/buscarPorCodUsuario/{codUsuario}")
	public ResponseEntity<Usuario> buscarPorCodUsuario(@PathVariable String codUsuario);
	
	@GetMapping("/usuario/welcome")
	public ResponseEntity<String> welcome();
	
	//Grupo
	
	@PostMapping("/grupo/crearGrupo/{nombre}/usuario/{codUsuario}")
	public ResponseEntity<Grupo> crearGrupo(@PathVariable String nombre, @PathVariable String codUsuario) throws Exception;
	
	@PostMapping("/grupo/anadirParticipante/{nombreGrupo}/usuario/{codUsuario}")
	public ResponseEntity<Grupo> añadirParticipante(@PathVariable String nombreGrupo, @PathVariable String codUsuario);
	
	@GetMapping("/grupo/buscarGrupo/{nombreGrupo}")
	public ResponseEntity<Grupo> buscarGrupo(@PathVariable String nombreGrupo);
	
	
	//Balance
	
	@PostMapping("/balance/anadirBalance")
	public ResponseEntity<Balance> anadirBalance(@RequestBody Balance balance);
	
}
