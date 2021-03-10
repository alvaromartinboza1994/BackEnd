
package com.proyectosPersonales.springboot.app.pago.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectosPersonales.springboot.app.commons.dto.*;
import com.proyectosPersonales.springboot.app.pago.client.UsuarioClienteRest;
import com.proyectosPersonales.springboot.app.pago.service.interfaces.PagoService;

import lombok.extern.slf4j.Slf4j;

@Service("serviceFeign")
@Slf4j
public class PagoServiceImpl implements PagoService {
	
	@Autowired
	private UsuarioClienteRest clienteFeign;
	
	@Override
	@Transactional
	public Usuario añadirUsuarioPago(UsuarioPago usuarioPago) {
		Usuario usuario_db = clienteFeign.buscarPorCodUsuario(usuarioPago.getCodUsuario()).getBody();
		if(usuario_db != null) {
			usuario_db.getMisPagos().add(Pago.builder()
					.importe((double) usuarioPago.getImporte())
					.descripcion(usuarioPago.getDescripcion())
					.fecha(usuarioPago.getFecha())
					.build());
			Grupo miGrupo = clienteFeign.buscarGrupo(usuario_db.getMiGrupo()).getBody();
			if(miGrupo != null) {
				miGrupo.getParticipantes().forEach(participante -> {
					Usuario participante_db = clienteFeign.buscarPorCodUsuario(participante.getCodUsuario()).getBody();
					if(participante_db != null && !usuarioPago.getCodUsuario().equals(participante_db.getCodUsuario())) {
						Deuda deuda = Deuda.builder()
								.importe((double) usuarioPago.getImporte() / miGrupo.getParticipantes().size())
								.descripcion(usuarioPago.getDescripcion())
								.fecha(usuarioPago.getFecha())
								.codPagador(usuario_db.getCodUsuario())
								.build();
						participante_db.getMisDeudas().add(deuda);
						clienteFeign.actualizarUsuario(participante_db);
					}			
				});
			}
		}
		return clienteFeign.actualizarUsuario(usuario_db).getBody();
	}

	@Override
	@Transactional
	public List<UsuarioPago> consultarPagosCompartidos(String nombreGrupo) {
		Grupo grupo_db = clienteFeign.buscarGrupo(nombreGrupo).getBody();
		List<UsuarioPago> pagosCompartidos = new ArrayList<>();
		if(grupo_db != null) {
			grupo_db.getParticipantes().forEach(participante -> {
				Usuario participante_db = clienteFeign.buscarPorCodUsuario(participante.getCodUsuario()).getBody();
				if(participante_db != null) {
					pagosCompartidos.addAll(participante_db.getMisPagos()
					.stream()
					.map(pago -> {
						UsuarioPago usuarioPago = UsuarioPago.builder()
								.codUsuario(participante_db.getCodUsuario())
								.importe((double) pago.getImporte())
								.descripcion(pago.getDescripcion())
								.fecha(pago.getFecha())
								.build();
						return usuarioPago;
					})
					.collect(Collectors.toList()));
				}
			});	
		}	
		
		return pagosCompartidos.stream()
				.sorted(Comparator.comparing(UsuarioPago::getFecha).reversed())
				.collect(Collectors.toList());
	}

	@Override
	@Transactional
	public List<Balance> calcularBalance(String nombreGrupo) {
		Grupo grupo_db = clienteFeign.buscarGrupo(nombreGrupo).getBody();
		List<UsuarioPago> pagosCompartidos = consultarPagosCompartidos(nombreGrupo);
		
		Double gastosPorPersona = (double) pagosCompartidos.stream()
			.map(usuarioPago -> usuarioPago.getImporte())
			.collect(Collectors.summingDouble(Double::doubleValue)) / grupo_db.getParticipantes().size(); 
		
		List<Balance> listaBalances = new ArrayList<>();
		
		grupo_db.getParticipantes().forEach(participante -> {
			Usuario participante_db = clienteFeign.buscarPorCodUsuario(participante.getCodUsuario()).getBody();
			if(participante_db != null) {
				Double gastosParticipante = participante_db.getMisPagos().stream()
						.map(Pago::getImporte)
						.collect(Collectors.summingDouble(Double::doubleValue));
				listaBalances.add(Balance.builder().codUsuario(participante_db.getCodUsuario()).importe(gastosParticipante - gastosPorPersona).build());
			}
		});		
		listaBalances.forEach(balance -> {
			clienteFeign.anadirBalance(balance);
		});
		return listaBalances;
	}

	@Override
	@Transactional
	public List<UsuarioDeuda> calcularMinimoPagos(String nombreGrupo) {
		List<UsuarioDeuda> deudasUnificadas = new ArrayList<>();
		Grupo grupo_db = clienteFeign.buscarGrupo(nombreGrupo).getBody();
		if(grupo_db != null) {
			deudasUnificadas = unificarPagos(grupo_db);
		}		
		return deudasUnificadas;
	}

	public List<UsuarioDeuda> unificarPagos(Grupo grupo) {
		List<UsuarioDeuda> usuariosDeudas = new ArrayList<>();
		grupo.getParticipantes().forEach(participante -> {
			grupo.getParticipantes().forEach(compi -> {
				if(!participante.getCodUsuario().equals(compi.getCodUsuario())) {
					List<Deuda> misDeudasUnificadas = participante.getMisDeudas().stream()
							.filter(deuda -> deuda.getCodPagador().equals(compi.getCodUsuario()))
							.collect(Collectors.toList());
					participante.getMisDeudas().removeAll(misDeudasUnificadas);
					Double totalDeuda = misDeudasUnificadas.stream()
						.map(Deuda::getImporte)
						.collect(Collectors.summingDouble(Double::doubleValue));
					if(totalDeuda > 0) {
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(new Date());
						usuariosDeudas.add(UsuarioDeuda.builder()
								.deudor(participante.getCodUsuario())
								.deuda(Deuda.builder()
										.importe(totalDeuda)
										.descripcion("Deuda Unificada")
										.fecha(calendar)
										.codPagador(compi.getCodUsuario())
										.build())
								.build());
						log.info(usuariosDeudas.toString());
						
					}
				}
			});
		});
		return usuariosDeudas;
	}
	
}
