
package com.proyectosPersonales.springboot.app.gastos.service.interfaces;

import java.util.List;

import com.proyectosPersonales.springboot.app.gastos.dto.Balance;
import com.proyectosPersonales.springboot.app.gastos.dto.Deuda;
import com.proyectosPersonales.springboot.app.gastos.dto.Usuario;
import com.proyectosPersonales.springboot.app.gastos.dto.UsuarioDeuda;
import com.proyectosPersonales.springboot.app.gastos.dto.UsuarioPago;

public interface PagoService {
	
	Usuario añadirUsuarioPago(UsuarioPago usuarioPago);
	
	List<UsuarioPago> consultarPagosCompartidos(String nombreGrupo);
	
	List<Balance> calcularBalance(String nombreGrupo);
	
	List<List<UsuarioDeuda>> calcularMinimoPagos(String nombreGrupo);
	
	List<List<UsuarioDeuda>> calcularMinimoPagos_v2(String nombreGrupo);

}
