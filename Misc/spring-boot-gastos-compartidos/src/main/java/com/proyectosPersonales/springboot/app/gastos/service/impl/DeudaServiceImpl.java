package com.proyectosPersonales.springboot.app.gastos.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectosPersonales.springboot.app.gastos.dto.Deuda;
import com.proyectosPersonales.springboot.app.gastos.exception.ApiException;
import com.proyectosPersonales.springboot.app.gastos.repository.DeudaDaoI;
import com.proyectosPersonales.springboot.app.gastos.service.interfaces.DeudaService;

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
	public void eliminarDeuda(Deuda deuda) {
		try {
			deudaDao.delete(deuda);
		} catch (Exception e) {
			throw new ApiException("PERSISTENCE_ERROR", "No se ha podido eliminar la deuda " + deuda.toString());
		}
	}

	@Override
	@Transactional
	public Deuda buscarDeudaPorIdDeuda(Integer idDeuda) {
		try {
			return deudaDao.findByIdDeuda(idDeuda);
		} catch (Exception e) {
			throw new ApiException("DEUDA_NOT_FOUND", "No se ha podido encontrar la deuda con Id: " + idDeuda);
		}
	}

}
