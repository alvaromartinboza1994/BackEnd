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
	public Deuda guardarDeuda(Deuda deuda) {
		return deudaDao.save(deuda);
	}

	@Override
	public void eliminarDeuda(Deuda deuda) {
		deudaDao.delete(deuda);
	}

	@Override
	public Deuda buscarDeudaPorIdDeuda(Integer idDeuda) {
		Deuda deuda_db = deudaDao.findByIdDeuda(idDeuda);
		if(deuda_db != null) {
			return deuda_db;
		}
		throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "No existe la deuda " + idDeuda);
	}

}
