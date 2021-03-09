package com.proyectosPersonales.springboot.app.service.balance;

import com.proyectosPersonales.springboot.app.gastos.dto.Balance;

public class BalanceServiceImplTestUtil {
	
	public static Balance crearBalanceCorrecto() {
		return Balance.builder()
				.importe(1D)
				.codUsuario("")
				.build();
	}  
	
	public static Balance crearBalanceVacio() {
		return Balance.builder()
				.build();
	}    
	
	public static Balance crearBalanceCorrecto2() {
		return Balance.builder()
				.importe(2D)
				.codUsuario("")
				.build();
	}    
}
