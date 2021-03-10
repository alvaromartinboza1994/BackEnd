package com.proyectosPersonales.springboot.app.usuario.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyectosPersonales.springboot.app.commons.dto.Balance;

public interface BalanceDaoI extends JpaRepository<Balance, Integer>{

}
