
package com.proyectosPersonales.springboot.app.gastos.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyectosPersonales.springboot.app.gastos.controller.interfaces.PagoControllerI;
import com.proyectosPersonales.springboot.app.gastos.dto.Balance;
import com.proyectosPersonales.springboot.app.gastos.dto.Usuario;
import com.proyectosPersonales.springboot.app.gastos.dto.UsuarioDeuda;
import com.proyectosPersonales.springboot.app.gastos.dto.UsuarioPago;
import com.proyectosPersonales.springboot.app.gastos.service.interfaces.PagoService;

@RestController
@RequestMapping("pago")
public class PagoControllerImpl implements PagoControllerI{

	@Autowired
	private PagoService pagoService;
	
	@Override
	public ResponseEntity<Usuario> añadirPago(UsuarioPago usuarioPago) {
		return new ResponseEntity<>(pagoService.añadirUsuarioPago(usuarioPago), HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<List<UsuarioPago>> consultarPagosCompartidos(String nombreGrupo) {
		return new ResponseEntity<>(pagoService.consultarPagosCompartidos(nombreGrupo), HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<List<Balance>> calcularBalance(String nombreGrupo) {
		return new ResponseEntity<>(pagoService.calcularBalance(nombreGrupo), HttpStatus.OK);
	}
	
	@Override
	public List<List<UsuarioDeuda>> calcularMinimoPagos(String nombreGrupo) {
		return pagoService.calcularMinimoPagos(nombreGrupo);
	}

}
