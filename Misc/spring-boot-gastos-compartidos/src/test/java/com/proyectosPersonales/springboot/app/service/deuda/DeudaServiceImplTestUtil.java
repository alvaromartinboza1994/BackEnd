package com.proyectosPersonales.springboot.app.service.deuda;

import com.proyectosPersonales.springboot.app.gastos.dto.Deuda;

public class DeudaServiceImplTestUtil {
	
	public static Deuda crearDeudaCorrecto() {
		return Deuda.builder()
				.idDeuda(1)
				.codPagador("1")
				.descripcion("")
				.build();
	}  
	
	public static Deuda crearDeudaVacio() {
		return Deuda.builder()
				.build();
	}    
	
	public static Deuda crearDeudaCorrecto2() {
		return Deuda.builder()
				.idDeuda(2)
				.codPagador("2")
				.descripcion("")
				.build();
	}    
}
