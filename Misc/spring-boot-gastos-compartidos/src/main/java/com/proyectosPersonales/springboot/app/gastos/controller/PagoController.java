
package com.proyectosPersonales.springboot.app.gastos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyectosPersonales.springboot.app.gastos.dto.UsuarioPago;
import com.proyectosPersonales.springboot.app.gastos.service.interfaces.PagoService;

@RestController
@RequestMapping("pago")
public class PagoController {

	@Autowired
	private PagoService pagoService;
	
	@PostMapping("/anadirPago")
	public void añadirPago(@RequestBody UsuarioPago usuarioPago) {
		pagoService.añadirUsuarioPago(usuarioPago);
	}

}
