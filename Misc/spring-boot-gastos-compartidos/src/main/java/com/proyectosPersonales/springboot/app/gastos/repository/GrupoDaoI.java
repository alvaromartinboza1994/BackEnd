package com.proyectosPersonales.springboot.app.gastos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyectosPersonales.springboot.app.gastos.dto.Grupo;

public interface GrupoDaoI extends JpaRepository<Grupo, Integer>{

}
