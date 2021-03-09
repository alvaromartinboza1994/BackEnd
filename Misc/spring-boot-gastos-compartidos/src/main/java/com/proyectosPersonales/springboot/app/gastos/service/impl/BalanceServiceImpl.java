package com.proyectosPersonales.springboot.app.gastos.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectosPersonales.springboot.app.gastos.dto.Balance;
import com.proyectosPersonales.springboot.app.gastos.exception.ApiException;
import com.proyectosPersonales.springboot.app.gastos.repository.BalanceDaoI;
import com.proyectosPersonales.springboot.app.gastos.service.interfaces.BalanceService;

@Service
public class BalanceServiceImpl implements BalanceService {
	
	@Autowired
	private BalanceDaoI balanceDao;

	@Override
	@Transactional
	public Balance añadirBalance(Balance balance) {
		try {
			return balanceDao.save(balance);	
		} catch (Exception e) {
			throw new ApiException("PERSISTENCE_ERROR", "No se ha podido guardar el balance " + balance.toString());
		}
	}



}
