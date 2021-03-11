
package com.proyectosPersonales.springboot.app.pago.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectosPersonales.springboot.app.commons.dto.Balance;
import com.proyectosPersonales.springboot.app.commons.dto.Deuda;
import com.proyectosPersonales.springboot.app.commons.dto.Grupo;
import com.proyectosPersonales.springboot.app.commons.dto.Pago;
import com.proyectosPersonales.springboot.app.commons.dto.Usuario;
import com.proyectosPersonales.springboot.app.commons.dto.UsuarioActualizar;
import com.proyectosPersonales.springboot.app.commons.dto.UsuarioDeuda;
import com.proyectosPersonales.springboot.app.commons.dto.UsuarioPago;
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
						clienteFeign.actualizarUsuario(UsuarioActualizar.builder().codUsuario_Antiguo(participante_db.getCodUsuario()).nuevo(participante_db).build());
					}			
				});
			}
		}
		return clienteFeign.actualizarUsuario(UsuarioActualizar.builder().codUsuario_Antiguo(usuario_db.getCodUsuario()).nuevo(usuario_db).build()).getBody();
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
	public List<List<UsuarioDeuda>> calcularMinimoPagos(String nombreGrupo) {
		List<Balance> balances = calcularBalance(nombreGrupo);
		
		List<List<UsuarioDeuda>> pagares = balances.stream()
			.filter(balanceP -> balanceP.getImporte() > 0)
			.map(balanceP -> {
				Double balancesNegativos = balances.stream()
											.filter(balanceN -> balanceN.getImporte() < 0)
											.map(balanceN -> balanceN.getImporte())
											.collect(Collectors.summingDouble(Double::doubleValue));
				return balances.stream()
				.filter(balanceN -> balanceN.getImporte() < 0)
				.map(balanceN -> {
					Double porcentaje = (double) (balanceP.getImporte() / balancesNegativos);
					Double aPagar = porcentaje * balanceN.getImporte();
					return UsuarioDeuda.builder()
							.deuda(Deuda.builder()
									.codPagador(balanceP.getCodUsuario())
									.importe(aPagar)
									.build())
							.deudor(balanceN.getCodUsuario())
							.build();
				})
				.collect(Collectors.toList());
			})
			.collect(Collectors.toList());
		return pagares;
	}
	
}
