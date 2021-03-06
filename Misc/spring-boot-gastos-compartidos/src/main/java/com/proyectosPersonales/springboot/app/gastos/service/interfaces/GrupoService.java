
package com.proyectosPersonales.springboot.app.gastos.service.interfaces;

import com.proyectosPersonales.springboot.app.gastos.dto.Grupo;

public interface GrupoService {

	Grupo crearGrupo(String nombreGrupo, String codUsuario) throws Exception;
	
	Grupo añadirParticipante(String nombreGrupo, String codUsuario);
	
	Grupo buscarGrupo(String nombreGrupo);

}
