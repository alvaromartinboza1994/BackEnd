package com.proyectosPersonales.springboot.app.usuario.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.proyectosPersonales.springboot.app.commons.dto.Balance;
import com.proyectosPersonales.springboot.app.usuario.packages.service.interfaces.BalanceService;

@RestController
public class BalanceControllerImpl implements BalanceControllerI{

	@Autowired
	private BalanceService balanceService;

	@Override
	public ResponseEntity<Balance> anadirBalance(Balance balance) {
		return new ResponseEntity<>(balanceService.añadirBalance(balance), HttpStatus.OK);
	}

}
