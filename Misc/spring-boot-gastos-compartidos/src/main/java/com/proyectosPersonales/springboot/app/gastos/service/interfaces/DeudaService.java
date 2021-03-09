package com.proyectosPersonales.springboot.app.gastos.service.interfaces;

import com.proyectosPersonales.springboot.app.gastos.dto.Deuda;

public interface DeudaService {

	Deuda guardarDeuda(Deuda deuda);
	
	Deuda buscarDeudaPorIdDeuda(Integer idDeuda);

}
