package com.proyectosPersonales.springboot.app.gastos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.proyectosPersonales.springboot.app.gastos.dto.Deuda;
import com.proyectosPersonales.springboot.app.gastos.dto.Usuario;
import com.proyectosPersonales.springboot.app.gastos.repository.DeudaDaoI;
import com.proyectosPersonales.springboot.app.gastos.repository.UsuarioDaoI;
import com.proyectosPersonales.springboot.app.gastos.service.interfaces.DeudaService;
import com.proyectosPersonales.springboot.app.gastos.service.interfaces.UsuarioService;

@Service
public class DeudaServiceImpl implements DeudaService {

	@Autowired
	DeudaDaoI deudaDao;

	@Override
	public void guardarDeuda(Deuda deuda) {
		deudaDao.save(deuda);
	}

	@Override
	public void eliminarDeuda(Deuda deuda) {
		deudaDao.delete(deuda);
	}

}
