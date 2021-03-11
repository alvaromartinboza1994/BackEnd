package com.proyectosPersonales.springboot.app.usuario.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyectosPersonales.springboot.app.commons.dto.Balance;

@Repository
public interface BalanceDaoI extends JpaRepository<Balance, Integer>{

}
