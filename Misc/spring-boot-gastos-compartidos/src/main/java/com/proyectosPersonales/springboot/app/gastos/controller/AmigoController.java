
package com.proyectosPersonales.springboot.app.gastos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyectosPersonales.springboot.app.gastos.dto.UsuarioAmigo;
import com.proyectosPersonales.springboot.app.gastos.dto.UsuarioPago;
import com.proyectosPersonales.springboot.app.gastos.service.interfaces.AmigoService;
import com.proyectosPersonales.springboot.app.gastos.service.interfaces.PagoService;

@RestController
@RequestMapping("amigo")
public class AmigoController {

	@Autowired
	private AmigoService amigoService;
	
	@PostMapping("/anadirAmigo")
	public void añadirPago(@RequestBody UsuarioAmigo usuarioAmigo) {
		amigoService.añadirUsuarioAmigo(usuarioAmigo);
	}

}
