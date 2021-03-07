
package com.proyectosPersonales.springboot.app.gastos.service.interfaces;

import com.proyectosPersonales.springboot.app.gastos.dto.Usuario;

public interface GrupoService {

	void crearGrupo(String nombreGrupo);
	
	void añadirParticipante(Usuario usuario);

}
