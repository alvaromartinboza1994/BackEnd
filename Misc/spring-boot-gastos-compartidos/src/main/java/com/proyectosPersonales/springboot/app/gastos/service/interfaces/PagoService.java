package com.proyectosPersonales.springboot.app.gastos.service.interfaces;

import com.proyectosPersonales.springboot.app.gastos.dto.PagoDTO;
import com.proyectosPersonales.springboot.app.gastos.dto.PersonaDTO;

public interface PagoService {
	
	PagoDTO buscarPorPagador(PersonaDTO persona);
	
	void guardarPago(PagoDTO pago);

}
