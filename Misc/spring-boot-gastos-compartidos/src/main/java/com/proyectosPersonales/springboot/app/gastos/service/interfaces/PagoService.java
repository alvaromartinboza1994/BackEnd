
package com.proyectosPersonales.springboot.app.gastos.service.interfaces;

import java.util.List;

import com.proyectosPersonales.springboot.app.gastos.dto.Balance;
import com.proyectosPersonales.springboot.app.gastos.dto.Deuda;
import com.proyectosPersonales.springboot.app.gastos.dto.UsuarioDeuda;
import com.proyectosPersonales.springboot.app.gastos.dto.UsuarioPago;

public interface PagoService {
	
	void añadirUsuarioPago(UsuarioPago usuarioPago);
	
	List<UsuarioPago> consultarPagosCompartidos(String nombreGrupo);
	
	List<Balance> calcularBalance(String nombreGrupo);
	
	List<UsuarioDeuda> calcularMinimoPagos(String nombreGrupo);

}
