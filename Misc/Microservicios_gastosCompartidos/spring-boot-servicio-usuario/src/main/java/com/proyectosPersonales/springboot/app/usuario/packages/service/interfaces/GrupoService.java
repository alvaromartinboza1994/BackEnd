
package com.proyectosPersonales.springboot.app.usuario.packages.service.interfaces;

import com.proyectosPersonales.springboot.app.commons.dto.Grupo;
import com.proyectosPersonales.springboot.app.commons.dto.Usuario;

public interface GrupoService {

	Grupo crearGrupo(String nombreGrupo, String codUsuario) throws Exception;

	Grupo añadirParticipante(String nombreGrupo, String codUsuario);

	Grupo buscarGrupo(String nombreGrupo);

}
