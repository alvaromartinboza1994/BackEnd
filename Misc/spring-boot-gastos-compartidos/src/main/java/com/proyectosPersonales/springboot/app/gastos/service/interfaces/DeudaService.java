package com.proyectosPersonales.springboot.app.gastos.service.interfaces;

import java.util.List;

import com.proyectosPersonales.springboot.app.gastos.dto.Deuda;

public interface DeudaService {

	void guardarDeuda(Deuda deuda);
	
	List<Deuda> buscarDeudaPorIdPagador(Integer idPagador);

}
