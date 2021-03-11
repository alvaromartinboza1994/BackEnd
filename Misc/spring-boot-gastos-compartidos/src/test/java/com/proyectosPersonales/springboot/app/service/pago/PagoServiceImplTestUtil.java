package com.proyectosPersonales.springboot.app.service.pago;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.assertj.core.util.Arrays;

import com.proyectosPersonales.springboot.app.gastos.dto.Pago;
import com.proyectosPersonales.springboot.app.gastos.dto.Usuario;
import com.proyectosPersonales.springboot.app.gastos.dto.UsuarioPago;

public class PagoServiceImplTestUtil {
	
	public static UsuarioPago crearUsuarioPago_Correcto(Usuario usuario) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		return UsuarioPago.builder()
				.codUsuario(usuario.getCodUsuario())
				.importe(1D)
				.descripcion("")
				.fecha(calendar)
				.build();
	}
	
	public static Usuario crearUsuarioCorrecto_ConPago() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		List<Pago> listaPagos = new ArrayList<>();
		listaPagos.add(Pago.builder()
				.importe(1D)
				.fecha(calendar)
				.build());
		return Usuario.builder()
				.nombre("N2")
				.apellidos("AP2")
				.codUsuario("NAP2")
				.misPagos(listaPagos)
				.misDeudas(new ArrayList<>())
				.build();
	}
	
	
    
}
