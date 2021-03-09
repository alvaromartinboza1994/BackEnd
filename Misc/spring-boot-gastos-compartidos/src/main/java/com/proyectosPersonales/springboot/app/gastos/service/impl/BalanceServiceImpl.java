package com.proyectosPersonales.springboot.app.gastos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectosPersonales.springboot.app.gastos.dto.Balance;
import com.proyectosPersonales.springboot.app.gastos.repository.BalanceDaoI;
import com.proyectosPersonales.springboot.app.gastos.service.interfaces.BalanceService;

@Service
public class BalanceServiceImpl implements BalanceService {
	
	@Autowired
	private BalanceDaoI balanceDao;

	@Override
	public Balance añadirBalance(Balance balance) {
		return balanceDao.save(balance);	
	}



}
