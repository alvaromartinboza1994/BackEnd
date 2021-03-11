package com.proyectosPersonales.springboot.app.usuario.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyectosPersonales.springboot.app.commons.dto.Usuario;

@Repository
public interface UsuarioDaoI extends JpaRepository<Usuario, Integer>{
	
	Usuario findByNombreAndApellidos(String nombre, String apellidos);
	
	Usuario findByCodUsuario(String codUsuario);
	
	Usuario findByIdUsuario(Integer idUsuario);

}
