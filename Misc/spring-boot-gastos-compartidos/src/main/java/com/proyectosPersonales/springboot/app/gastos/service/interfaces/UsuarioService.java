package com.proyectosPersonales.springboot.app.gastos.service.interfaces;

import com.proyectosPersonales.springboot.app.gastos.dto.Usuario;

public interface UsuarioService {

	Usuario buscarPorNombreYApellidos(String nombre, String apellidos);

	void guardarUsuario(Usuario usuario);

}
