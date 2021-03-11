package com.proyectosPersonales.springboot.app.gastos.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.proyectosPersonales.springboot.app.gastos.dto.Usuario;
import com.proyectosPersonales.springboot.app.gastos.dto.UsuarioActualizar;
import com.proyectosPersonales.springboot.app.gastos.dto.UsuarioContrasena;
import com.proyectosPersonales.springboot.app.gastos.exception.ApiException;
import com.proyectosPersonales.springboot.app.gastos.repository.UsuarioDaoI;
import com.proyectosPersonales.springboot.app.gastos.service.interfaces.UsuarioContrasenaService;
import com.proyectosPersonales.springboot.app.gastos.service.interfaces.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioDaoI usuarioDao;
	
	@Autowired
	private UsuarioContrasenaService usuarioContrasenaService;

	@Override
	@Transactional
	public Usuario guardarUsuario(UsuarioContrasena signup) {
		try {
			usuarioDao.save(signup.getUsuario());
			UsuarioContrasena usuarioContrasena = usuarioContrasenaService.guardarUsuarioContrasena(signup);
			return usuarioContrasena.getUsuario();
		} catch (Exception e) {
			throw new ApiException("PERSISTENCE_ERROR", "No se ha podido guardar el usuario " + signup.toString());
		}
	}

	@Override
	@Transactional
	public Usuario buscarPorCodUsuario(String codUsuario) {
		Usuario usuario_db = usuarioDao.findByCodUsuario(codUsuario);
		if(usuario_db != null) {
			return usuario_db;
		} else {
			throw new ApiException("USER_NOT_FOUND", "No existe el usuario " + codUsuario);
		}
	}

	@Override
	@Transactional
	public Usuario buscarPorIdUsuario(Integer idUsuario) {
		Usuario usuario_db = usuarioDao.findByIdUsuario(idUsuario);
		if(usuario_db != null) {
			return usuario_db;
		} else {
			throw new ApiException("USER_NOT_FOUND", "No existe el usuario con Id: " + idUsuario);
		}
	}

	@Override
	@Transactional
	public ResponseEntity<String> accederUsuario(UsuarioContrasena signup) {
		UsuarioContrasena usuarioContrasena = usuarioContrasenaService.buscarPorUsuarioCodUsuario(signup.getUsuario().getCodUsuario());
		if(signup.getContraseña().equals(usuarioContrasena.getContraseña())) {
			return new ResponseEntity<>("Acceso realizado con éxito!", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("La contraseña no coincide con la registrada en el sistema,", HttpStatus.FORBIDDEN);
		}
	}

	@Override
	@Transactional
	public Usuario actualizarUsuario(UsuarioActualizar usuario) {
		Usuario usuario_db = buscarPorCodUsuario(usuario.getCodUsuario_Antiguo());
		try {
			return usuarioDao.save(Usuario.builder()
					.idUsuario(usuario_db.getIdUsuario())
					.nombre(usuario.getNuevo() != null && usuario.getNuevo().getNombre() != null ? 
							usuario.getNuevo().getNombre() : usuario_db.getNombre())
					.apellidos(usuario.getNuevo() != null && usuario.getNuevo().getApellidos() != null ? 
							usuario.getNuevo().getApellidos() : usuario_db.getApellidos())
					.codUsuario(usuario.getNuevo() != null && usuario.getNuevo().getCodUsuario() != null ? 
							usuario.getNuevo().getCodUsuario() : usuario_db.getCodUsuario())
					.misPagos(usuario.getNuevo() != null && usuario.getNuevo().getMisPagos() != null ? 
							usuario.getNuevo().getMisPagos() : usuario_db.getMisPagos())
					.misDeudas(usuario.getNuevo() != null && usuario.getNuevo().getMisDeudas() != null ? 
							usuario.getNuevo().getMisDeudas() : usuario_db.getMisDeudas())
					.miGrupo(usuario.getNuevo() != null && usuario.getNuevo().getMiGrupo() != null ? 
							usuario.getNuevo().getMiGrupo() : usuario_db.getMiGrupo())
					.build());
		} catch (Exception e) {
			throw new ApiException("USER_UPDATE", "No se ha podido actualizar al usuario " + usuario.toString());
		}
	}

}
