
package com.proyectosPersonales.springboot.app.pago.service.interfaces;

import java.util.List;

import com.proyectosPersonales.springboot.app.commons.dto.Balance;
import com.proyectosPersonales.springboot.app.commons.dto.Usuario;
import com.proyectosPersonales.springboot.app.commons.dto.UsuarioDeuda;
import com.proyectosPersonales.springboot.app.commons.dto.UsuarioPago;

public interface PagoService {
	
	Usuario añadirUsuarioPago(UsuarioPago usuarioPago);
	
	List<UsuarioPago> consultarPagosCompartidos(String nombreGrupo);
	
	List<Balance> calcularBalance(String nombreGrupo);
	
	List<List<UsuarioDeuda>> calcularMinimoPagos(String nombreGrupo);

}
