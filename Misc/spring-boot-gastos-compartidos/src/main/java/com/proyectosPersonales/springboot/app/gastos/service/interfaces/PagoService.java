
package com.proyectosPersonales.springboot.app.gastos.service.interfaces;

import java.util.List;

import com.proyectosPersonales.springboot.app.gastos.dto.Balance;
import com.proyectosPersonales.springboot.app.gastos.dto.UsuarioPago;

public interface PagoService {
	
	void añadirUsuarioPago(UsuarioPago usuarioPago);
	
	List<UsuarioPago> consultarPagosCompartidos(String codUsuario);
	
	List<Balance> calcularBalance(String codUsuario);

}
