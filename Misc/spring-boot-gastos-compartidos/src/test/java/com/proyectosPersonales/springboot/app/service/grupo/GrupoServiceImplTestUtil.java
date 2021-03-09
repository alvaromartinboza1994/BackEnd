package com.proyectosPersonales.springboot.app.service.grupo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.proyectosPersonales.springboot.app.gastos.dto.Grupo;
import com.proyectosPersonales.springboot.app.gastos.dto.GrupoPK;
import com.proyectosPersonales.springboot.app.gastos.dto.Usuario;

public class GrupoServiceImplTestUtil {
	
	public static Grupo crearGrupoCorrecto() {
		return Grupo.builder()
				.id(GrupoPK.builder()
						.nombreGrupo("misAmigos")
						.build())
				.participantes(new ArrayList<>())
				.build();
	} 
	
	public static Grupo crearGrupoCorrecto_ConParticipante(Usuario usuario) {
		return Grupo.builder()
				.id(GrupoPK.builder()
						.nombreGrupo("misAmigos")
						.build())
				.participantes(Arrays.asList(usuario))
				.build();
	} 
	
	public static Grupo crearGrupoCorrecto_ConParticipantes(List<Usuario> usuarios) {
		return Grupo.builder()
				.id(GrupoPK.builder()
						.nombreGrupo("misAmigos")
						.build())
				.participantes(usuarios)
				.build();
	}  
	
	public static Grupo crearGrupoVacio() {
		return Grupo.builder()
				.build();
	}    
	
	public static Grupo crearGrupoCorrecto2() {
		return Grupo.builder()
				.id(GrupoPK.builder()
						.nombreGrupo("misAmigos2")
						.build())
				.build();
	}    
}
