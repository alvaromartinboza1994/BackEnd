
package com.proyectosPersonales.springboot.app.gastos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyectosPersonales.springboot.app.gastos.dto.Balance;
import com.proyectosPersonales.springboot.app.gastos.dto.Usuario;
import com.proyectosPersonales.springboot.app.gastos.dto.UsuarioDeuda;
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
	
	@GetMapping("/consultarPagosCompartidos/{nombreGrupo}")
	public List<UsuarioPago> consultarPagosCompartidos(@PathVariable String nombreGrupo) {
		return pagoService.consultarPagosCompartidos(nombreGrupo);
	}
	
	@GetMapping("/calcularBalance/{nombreGrupo}")
	public List<Balance> calcularBalance(@PathVariable String nombreGrupo) {
		return pagoService.calcularBalance(nombreGrupo);
	}
	
	@GetMapping("/calcularMinimoPagos/{nombreGrupo}")
	public List<UsuarioDeuda> calcularMinimoPagos(@PathVariable String nombreGrupo) {
		return pagoService.calcularMinimoPagos(nombreGrupo);
	}

}
