
package com.proyectosPersonales.springboot.app.gastos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectosPersonales.springboot.app.gastos.dto.Usuario;
import com.proyectosPersonales.springboot.app.gastos.dto.UsuarioPago;
import com.proyectosPersonales.springboot.app.gastos.repository.PagoDaoI;
import com.proyectosPersonales.springboot.app.gastos.repository.UsuarioDaoI;
import com.proyectosPersonales.springboot.app.gastos.service.interfaces.PagoService;
import com.proyectosPersonales.springboot.app.gastos.service.interfaces.UsuarioService;

@Service
public class PagoServiceImpl implements PagoService {

	@Autowired
	private PagoDaoI pagoDao;
	
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	private UsuarioDaoI usuarioRepository;
	
	@Override
	public void añadirPagoUsuario(UsuarioPago usuarioPago) {
		Usuario usuario = usuarioService.buscarPorNombreYApellidos(usuarioPago.getUsuario().getNombre(),
				usuarioPago.getUsuario().getApellidos());
		usuario.getMisPagos().add(usuarioPago.getPago());
		usuarioRepository.save(usuario);
	}
}
