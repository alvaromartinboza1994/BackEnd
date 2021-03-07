
package com.proyectosPersonales.springboot.app.gastos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectosPersonales.springboot.app.gastos.dto.Amigo;
import com.proyectosPersonales.springboot.app.gastos.dto.Usuario;
import com.proyectosPersonales.springboot.app.gastos.dto.UsuarioAmigo;
import com.proyectosPersonales.springboot.app.gastos.repository.GrupoDaoI;
import com.proyectosPersonales.springboot.app.gastos.repository.UsuarioDaoI;
import com.proyectosPersonales.springboot.app.gastos.service.interfaces.AmigoService;
import com.proyectosPersonales.springboot.app.gastos.service.interfaces.GrupoService;
import com.proyectosPersonales.springboot.app.gastos.service.interfaces.UsuarioService;

@Service
public class GrupoServiceImpl implements GrupoService {

	@Autowired
	GrupoDaoI grupoDao;

	@Override
	public void crearGrupo(String nombreGrupo) {
		
	}

	@Override
	public void añadirParticipante(Usuario usuario) {
		// TODO Auto-generated method stub
		
	}
}
