package com.proyectosPersonales.springboot.app.gastos.service.interfaces;

import com.proyectosPersonales.springboot.app.gastos.dto.Deuda;

public interface DeudaService {

	void guardarDeuda(Deuda deuda);
	
	void eliminarDeuda(Deuda deuda);

}
