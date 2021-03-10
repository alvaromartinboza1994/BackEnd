
package com.proyectosPersonales.springboot.app.pago.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.proyectosPersonales.springboot.app.commons.dto.Balance;
import com.proyectosPersonales.springboot.app.commons.dto.Usuario;
import com.proyectosPersonales.springboot.app.commons.dto.UsuarioDeuda;
import com.proyectosPersonales.springboot.app.commons.dto.UsuarioPago;


public interface PagoControllerI {
	
	@PostMapping("/anadirPago")
	public ResponseEntity<Usuario> añadirPago(@RequestBody UsuarioPago usuarioPago);
	
	@GetMapping("/consultarPagosCompartidos/{nombreGrupo}")
	public ResponseEntity<List<UsuarioPago>> consultarPagosCompartidos(@PathVariable String nombreGrupo);
	
	@GetMapping("/calcularBalance/{nombreGrupo}")
	public ResponseEntity<List<Balance>> calcularBalance(@PathVariable String nombreGrupo);
	
	@GetMapping("/calcularMinimoPagos/{nombreGrupo}")
	public ResponseEntity<List<UsuarioDeuda>> calcularMinimoPagos(@PathVariable String nombreGrupo);
}
