package com.proyectosPersonales.springboot.app.service.deuda;

import java.util.Calendar;
import java.util.Date;

import com.proyectosPersonales.springboot.app.gastos.dto.Deuda;

public class DeudaServiceImplTestUtil {
	
	private static Calendar calendar = Calendar.getInstance();
	private static Date date = new Date(0);
	
	public static Deuda crearDeudaCorrecto() {
		calendar.setTime(date);
		return Deuda.builder()
				.idDeuda(1)
				.importe(1D)
				.codPagador("1")
				.descripcion("")
				.fecha(calendar)
				.build();
	}  
	
	public static Deuda crearDeudaVacio() {
		return Deuda.builder()
				.build();
	}    
	
	public static Deuda crearDeudaCorrecto2() {
		calendar.setTime(date);
		return Deuda.builder()
				.idDeuda(2)
				.importe(2D)
				.codPagador("2")
				.descripcion("")
				.fecha(calendar)
				.build();
	}    
}
