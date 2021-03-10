package com.proyectosPersonales.springboot.app.usuario.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectosPersonales.springboot.app.commons.dto.Deuda;
import com.proyectosPersonales.springboot.app.commons.exception.ApiException;
import com.proyectosPersonales.springboot.app.usuario.dao.DeudaDaoI;
import com.proyectosPersonales.springboot.app.usuario.packages.service.interfaces.DeudaService;

@Service
public class DeudaServiceImpl implements DeudaService {

	@Autowired
	DeudaDaoI deudaDao;

	@Override
	@Transactional
	public Deuda guardarDeuda(Deuda deuda) {
		try {
			return deudaDao.save(deuda);
		} catch (Exception e) {
			throw new ApiException("PERSISTENCE_ERROR", "No se ha podido guardar la deuda " + deuda.toString());
		}
	}

	@Override
	@Transactional
	public Deuda buscarDeudaPorIdDeuda(Integer idDeuda) {
		Deuda deuda = deudaDao.findByIdDeuda(idDeuda);
		if(deuda != null) {
			return deuda;
		} else {
			throw new ApiException("DEUDA_NOT_FOUND", "No se ha podido encontrar la deuda con Id: " + idDeuda);
		}
	}

}
