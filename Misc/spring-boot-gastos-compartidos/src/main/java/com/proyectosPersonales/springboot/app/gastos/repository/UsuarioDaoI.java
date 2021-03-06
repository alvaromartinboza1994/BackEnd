package com.proyectosPersonales.springboot.app.gastos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyectosPersonales.springboot.app.gastos.dto.Usuario;
import com.proyectosPersonales.springboot.app.gastos.dto.UsuarioPk;

public interface UsuarioDaoI extends JpaRepository<Usuario, UsuarioPk>{
	
	//@Query("SELECT u FROM Usuario u WHERE u.nombre = ?1")
	public Usuario findByIdNombreAndIdApellidos(String nombre, String apellidos);

}
