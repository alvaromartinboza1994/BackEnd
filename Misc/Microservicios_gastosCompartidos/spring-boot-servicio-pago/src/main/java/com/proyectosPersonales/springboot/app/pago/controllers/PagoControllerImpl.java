
package com.proyectosPersonales.springboot.app.pago.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyectosPersonales.springboot.app.commons.dto.Balance;
import com.proyectosPersonales.springboot.app.commons.dto.Usuario;
import com.proyectosPersonales.springboot.app.commons.dto.UsuarioDeuda;
import com.proyectosPersonales.springboot.app.commons.dto.UsuarioPago;
import com.proyectosPersonales.springboot.app.pago.service.interfaces.PagoService;

@RefreshScope
@RestController
public class PagoControllerImpl implements PagoControllerI{

	@Autowired
	@Qualifier("serviceFeign") 
	private PagoService pagoService;
	
	@Override
	public ResponseEntity<Usuario> añadirPago(@RequestBody UsuarioPago usuarioPago) {
		return new ResponseEntity<>(pagoService.añadirUsuarioPago(usuarioPago), HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<List<UsuarioPago>> consultarPagosCompartidos(@PathVariable String nombreGrupo) {
		return new ResponseEntity<>(pagoService.consultarPagosCompartidos(nombreGrupo), HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<List<Balance>> calcularBalance(@PathVariable String nombreGrupo) {
		return new ResponseEntity<>(pagoService.calcularBalance(nombreGrupo), HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<List<UsuarioDeuda>> calcularMinimoPagos(@PathVariable String nombreGrupo) {
		return new ResponseEntity<>(pagoService.calcularMinimoPagos(nombreGrupo), HttpStatus.OK);
	}

}
