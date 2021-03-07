
package com.proyectosPersonales.springboot.app.gastos.service.interfaces;

import com.proyectosPersonales.springboot.app.gastos.dto.Grupo;
import com.proyectosPersonales.springboot.app.gastos.dto.Usuario;

public interface GrupoService {

	void crearGrupo(String nombreGrupo, String codUsuario);
	
	void añadirParticipante(String nombreGrupo, String codUsuario);
	
	Grupo buscarGrupo(String nombreGrupo);

}
