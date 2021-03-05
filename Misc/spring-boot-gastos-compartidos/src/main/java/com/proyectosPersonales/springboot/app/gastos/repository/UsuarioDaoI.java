package com.proyectosPersonales.springboot.app.gastos.repository;

import org.springframework.data.repository.CrudRepository;

import com.proyectosPersonales.springboot.app.gastos.dto.UsuarioDTO;

public interface UsuarioDaoI extends CrudRepository<UsuarioDTO, Integer>{

}
