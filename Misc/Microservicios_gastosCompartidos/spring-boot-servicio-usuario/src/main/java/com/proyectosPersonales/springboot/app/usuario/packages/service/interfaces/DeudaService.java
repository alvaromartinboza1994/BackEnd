package com.proyectosPersonales.springboot.app.usuario.packages.service.interfaces;

import com.proyectosPersonales.springboot.app.commons.dto.Deuda;

public interface DeudaService {

	Deuda guardarDeuda(Deuda deuda);
	
	Deuda buscarDeudaPorIdDeuda(Integer idDeuda);

}
