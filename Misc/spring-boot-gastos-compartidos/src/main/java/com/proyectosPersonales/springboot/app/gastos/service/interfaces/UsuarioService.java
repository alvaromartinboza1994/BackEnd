package com.proyectosPersonales.springboot.app.gastos.service.interfaces;

import com.proyectosPersonales.springboot.app.gastos.dto.Usuario;

public interface UsuarioService {

	Usuario buscarPorNombreYApellidos(String nombre, String apellidos);
	
	Usuario buscarPorCodUsuario(String codUsuario);
	
	Usuario buscarPorIdUsuario(Integer idUsuario);

	Usuario guardarUsuario(Usuario usuario);

}
