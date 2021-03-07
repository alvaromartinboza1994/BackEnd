
package com.proyectosPersonales.springboot.app.gastos.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.proyectosPersonales.springboot.app.gastos.dto.Balance;
import com.proyectosPersonales.springboot.app.gastos.dto.Deuda;
import com.proyectosPersonales.springboot.app.gastos.dto.Grupo;
import com.proyectosPersonales.springboot.app.gastos.dto.Pago;
import com.proyectosPersonales.springboot.app.gastos.dto.Usuario;
import com.proyectosPersonales.springboot.app.gastos.dto.UsuarioPago;
import com.proyectosPersonales.springboot.app.gastos.service.interfaces.PagoService;
import com.proyectosPersonales.springboot.app.gastos.service.interfaces.UsuarioService;

@Service
public class PagoServiceImpl implements PagoService {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Override
	public void añadirUsuarioPago(UsuarioPago usuarioPago) {
		Usuario usuario_db = usuarioService.buscarPorCodUsuario(usuarioPago.getCodUsuario());
		if(usuario_db != null) {
			usuario_db.getMisPagos().add(Pago.builder()
					.importe((long) usuarioPago.getImporte())
					.descripcion(usuarioPago.getDescripcion())
					.fecha(usuarioPago.getFecha())
					.build());
			Grupo m
			usuario_db.getMisAmigos().forEach(amigo -> {
				Usuario amigo_db = usuarioService.buscarPorCodUsuario(amigo.getCodAmigo());
				if(amigo_db != null) {
					Deuda deuda = Deuda.builder()
							.importe((long) usuarioPago.getImporte() / (usuario_db.getMisAmigos().size() + 1))
							.descripcion(usuarioPago.getDescripcion())
							.fecha(usuarioPago.getFecha())
							.idPagador(usuario_db.getIdUsuario())
							.build();
					amigo_db.getMisDeudas().add(deuda);
					usuarioService.guardarUsuario(amigo_db);
				}			
			});
			usuarioService.guardarUsuario(usuario_db);
		}
	}

	@Override
	public List<UsuarioPago> consultarPagosCompartidos(String codUsuario) {
		Usuario usuario_db = usuarioService.buscarPorCodUsuario(codUsuario);
		if(usuario_db != null) {
			//primero añadimos los pagos del usuario
			List<UsuarioPago> pagosCompartidos = usuario_db.getMisPagos()
					.stream()
					.map(pago -> {
						UsuarioPago usuarioPago = UsuarioPago.builder()
								.codUsuario(usuario_db.getCodUsuario())
								.importe(pago.getImporte())
								.descripcion(pago.getDescripcion())
								.fecha(pago.getFecha())
								.build();
						return usuarioPago;
					})
					.collect(Collectors.toList());
			//luego añadimos los pagos de los amigos
			usuario_db.getMisAmigos().forEach(amigo -> {
				Usuario amigo_db = usuarioService.buscarPorCodUsuario(amigo.getCodAmigo());
				if(amigo_db != null) {
					pagosCompartidos.addAll(amigo_db.getMisPagos()
					.stream()
					.map(pago -> {
						UsuarioPago usuarioPago = UsuarioPago.builder()
								.codUsuario(amigo_db.getCodUsuario())
								.importe(pago.getImporte())
								.descripcion(pago.getDescripcion())
								.fecha(pago.getFecha())
								.build();
						return usuarioPago;
					})
					.collect(Collectors.toList()));
				}
			});			
			
			return pagosCompartidos.stream()
					.sorted(Comparator.comparing(UsuarioPago::getFecha).reversed())
					.collect(Collectors.toList());
		}
		return null;
	}

	@Override
	public List<Balance> calcularBalance(String codUsuario) {
		Usuario usuario_db = usuarioService.buscarPorCodUsuario(codUsuario);
		if(usuario_db != null) {
			List<UsuarioPago> pagosCompartidos = consultarPagosCompartidos(codUsuario);
			
			Long gastosPorPersona = (long) pagosCompartidos.stream()
				.map(usuarioPago -> usuarioPago.getImporte())
				.collect(Collectors.summingLong(Long::longValue)) / usuario_db.getMisAmigos().size(); 
			
			Long misGastos = usuario_db.getMisPagos().stream()
					.map(Pago::getImporte)
					.collect(Collectors.summingLong(Long::longValue));
			
			List<Balance> listaBalances = new ArrayList<>();
			listaBalances.add(Balance.builder().codUsuario(usuario_db.getCodUsuario()).importe(misGastos - gastosPorPersona).build());
			
			usuario_db.getMisAmigos().forEach(amigo -> {
				Usuario amigo_db = usuarioService.buscarPorCodUsuario(amigo.getCodAmigo());
				if(amigo_db != null) {
					Long gastosAmigo = amigo_db.getMisPagos().stream()
							.map(Pago::getImporte)
							.collect(Collectors.summingLong(Long::longValue));
					listaBalances.add(Balance.builder().codUsuario(amigo_db.getCodUsuario()).importe(gastosAmigo - gastosPorPersona).build());
				}
			});		
			return listaBalances;
		}		
		return null;
	}
}
