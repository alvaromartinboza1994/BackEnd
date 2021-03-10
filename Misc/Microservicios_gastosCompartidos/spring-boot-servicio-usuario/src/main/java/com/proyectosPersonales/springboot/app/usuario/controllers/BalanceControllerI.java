
package com.proyectosPersonales.springboot.app.usuario.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.proyectosPersonales.springboot.app.commons.dto.Balance;
import com.proyectosPersonales.springboot.app.commons.dto.Grupo;

public interface BalanceControllerI {

	@PostMapping("/anadirBalance")
	public ResponseEntity<Balance> anadirBalance(@RequestBody Balance balance);

}
