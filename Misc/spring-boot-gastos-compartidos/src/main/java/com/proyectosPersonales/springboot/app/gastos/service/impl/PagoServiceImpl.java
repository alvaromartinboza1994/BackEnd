package com.proyectosPersonales.springboot.app.gastos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.proyectosPersonales.springboot.app.gastos.dto.PagoDTO;
import com.proyectosPersonales.springboot.app.gastos.dto.PersonaDTO;
import com.proyectosPersonales.springboot.app.gastos.repository.PagoDaoI;
import com.proyectosPersonales.springboot.app.gastos.repository.PersonaDaoI;
import com.proyectosPersonales.springboot.app.gastos.service.interfaces.PagoService;

public class PagoServiceImpl implements PagoService{
	
	@Autowired
	private PagoDaoI pagoDao;

	@Override
	public PagoDTO buscarPorPagador(PersonaDTO persona) {
		return pagoDao.findByPagador(persona);
	}

	@Override
	public void guardarPago(PagoDTO pago) {
		pagoDao.save(pago);
	}

}
