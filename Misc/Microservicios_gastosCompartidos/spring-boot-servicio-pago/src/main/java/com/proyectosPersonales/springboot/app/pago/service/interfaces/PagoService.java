
package com.proyectosPersonales.springboot.app.pago.service.interfaces;

import java.util.List;

import com.proyectosPersonales.springboot.app.commons.dto.*;

public interface PagoService {
	
	Usuario añadirUsuarioPago(UsuarioPago usuarioPago);
	
	List<UsuarioPago> consultarPagosCompartidos(String nombreGrupo);
	
	List<Balance> calcularBalance(String nombreGrupo);
	
	List<UsuarioDeuda> calcularMinimoPagos(String nombreGrupo);

}
