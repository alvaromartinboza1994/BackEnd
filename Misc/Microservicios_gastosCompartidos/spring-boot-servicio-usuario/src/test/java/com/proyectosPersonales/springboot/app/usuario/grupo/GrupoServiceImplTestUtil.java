package com.proyectosPersonales.springboot.app.usuario.grupo;

import java.util.ArrayList;
import java.util.Arrays;

import com.proyectosPersonales.springboot.app.commons.dto.Grupo;
import com.proyectosPersonales.springboot.app.commons.dto.GrupoPK;
import com.proyectosPersonales.springboot.app.commons.dto.Usuario;

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
	
}
