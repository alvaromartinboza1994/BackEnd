package com.proyectosPersonales.springboot.app.service.usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Arrays;

import com.proyectosPersonales.springboot.app.gastos.dto.Deuda;
import com.proyectosPersonales.springboot.app.gastos.dto.Pago;
import com.proyectosPersonales.springboot.app.gastos.dto.Usuario;

public class UsuarioServiceImplTestUtil {
	
	public static Usuario crearUsuarioCorrecto() {
		List<Deuda> listaDeudas = new ArrayList<>();
		listaDeudas.add(Deuda.builder().importe(1D).codPagador("NAP2").build());
		listaDeudas.add(Deuda.builder().importe(2D).codPagador("NAP2").build());
		return Usuario.builder()
				.nombre("N")
				.apellidos("AP1")
				.codUsuario("NAP1")
				.misPagos(new ArrayList<>())
				.misDeudas(listaDeudas)
				.build();
	}
	
	public static Usuario crearUsuarioCorrecto_ConGrupo() {
		List<Pago> listaPagos = new ArrayList<>();
		listaPagos.add(Pago.builder()
				.importe(1D)
				.build());
		return Usuario.builder()
				.nombre("N")
				.apellidos("AP1")
				.codUsuario("NAP1")
				.miGrupo("misAmigos")
				.misPagos(listaPagos)
				.misDeudas(new ArrayList<>())
				.build();
	}
	
	public static Usuario crearUsuarioCorrecto2() {
		List<Deuda> listaDeudas = new ArrayList<>();
		listaDeudas.add(Deuda.builder().importe(1D).codPagador("NAP1").build());
		listaDeudas.add(Deuda.builder().importe(2D).codPagador("NAP1").build());
		return Usuario.builder()
				.nombre("N2")
				.apellidos("AP2")
				.codUsuario("NAP2")
				.misPagos(new ArrayList<>())
				.misDeudas(listaDeudas)
				.build();
	}
	
	public static Usuario crearUsuarioVacio() {
		return Usuario.builder()
				.build();
	}
	
	public static List<Usuario> crearListaUsuarios() {
		List<Usuario> usuarios = new ArrayList<>();
		usuarios.add(crearUsuarioCorrecto()); usuarios.add(crearUsuarioCorrecto2());
		return usuarios;
	}

    
}
