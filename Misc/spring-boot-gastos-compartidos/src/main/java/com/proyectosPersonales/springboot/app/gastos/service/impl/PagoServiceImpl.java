
package com.proyectosPersonales.springboot.app.gastos.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectosPersonales.springboot.app.gastos.dto.Balance;
import com.proyectosPersonales.springboot.app.gastos.dto.Deuda;
import com.proyectosPersonales.springboot.app.gastos.dto.Grupo;
import com.proyectosPersonales.springboot.app.gastos.dto.Pago;
import com.proyectosPersonales.springboot.app.gastos.dto.Usuario;
import com.proyectosPersonales.springboot.app.gastos.dto.UsuarioDeuda;
import com.proyectosPersonales.springboot.app.gastos.dto.UsuarioPago;
import com.proyectosPersonales.springboot.app.gastos.service.interfaces.BalanceService;
import com.proyectosPersonales.springboot.app.gastos.service.interfaces.GrupoService;
import com.proyectosPersonales.springboot.app.gastos.service.interfaces.PagoService;
import com.proyectosPersonales.springboot.app.gastos.service.interfaces.UsuarioService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PagoServiceImpl implements PagoService {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private GrupoService grupoService;
	
	@Autowired
	private BalanceService balanceService;
	
	@Override
	@Transactional
	public Usuario añadirUsuarioPago(UsuarioPago usuarioPago) {
		Usuario usuario_db = usuarioService.buscarPorCodUsuario(usuarioPago.getCodUsuario());
		if(usuario_db != null) {
			usuario_db.getMisPagos().add(Pago.builder()
					.importe((double) usuarioPago.getImporte())
					.descripcion(usuarioPago.getDescripcion())
					.fecha(usuarioPago.getFecha())
					.build());
			Grupo miGrupo = grupoService.buscarGrupo(usuario_db.getMiGrupo());
			if(miGrupo != null) {
				miGrupo.getParticipantes().forEach(participante -> {
					Usuario participante_db = usuarioService.buscarPorCodUsuario(participante.getCodUsuario());
					if(participante_db != null && !usuarioPago.getCodUsuario().equals(participante_db.getCodUsuario())) {
						Deuda deuda = Deuda.builder()
								.importe((double) usuarioPago.getImporte() / miGrupo.getParticipantes().size())
								.descripcion(usuarioPago.getDescripcion())
								.fecha(usuarioPago.getFecha())
								.codPagador(usuario_db.getCodUsuario())
								.build();
						participante_db.getMisDeudas().add(deuda);
						usuarioService.actualizarUsuario(participante_db);
					}			
				});
			}
		}
		return usuarioService.actualizarUsuario(usuario_db);
	}

	@Override
	@Transactional
	public List<UsuarioPago> consultarPagosCompartidos(String nombreGrupo) {
		Grupo grupo_db = grupoService.buscarGrupo(nombreGrupo);
		List<UsuarioPago> pagosCompartidos = new ArrayList<>();
		if(grupo_db != null) {
			grupo_db.getParticipantes().forEach(participante -> {
				Usuario participante_db = usuarioService.buscarPorCodUsuario(participante.getCodUsuario());
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
		Grupo grupo_db = grupoService.buscarGrupo(nombreGrupo);
		List<UsuarioPago> pagosCompartidos = consultarPagosCompartidos(nombreGrupo);
		
		Double gastosPorPersona = (double) pagosCompartidos.stream()
			.map(usuarioPago -> usuarioPago.getImporte())
			.collect(Collectors.summingDouble(Double::doubleValue)) / grupo_db.getParticipantes().size(); 
		
		List<Balance> listaBalances = new ArrayList<>();
		
		grupo_db.getParticipantes().forEach(participante -> {
			Usuario participante_db = usuarioService.buscarPorCodUsuario(participante.getCodUsuario());
			if(participante_db != null) {
				Double gastosParticipante = participante_db.getMisPagos().stream()
						.map(Pago::getImporte)
						.collect(Collectors.summingDouble(Double::doubleValue));
				listaBalances.add(Balance.builder().codUsuario(participante_db.getCodUsuario()).importe(gastosParticipante - gastosPorPersona).build());
			}
		});		
		listaBalances.forEach(balance -> {
			balanceService.añadirBalance(balance);
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

	/*
	 * @Override public List<List<UsuarioDeuda>> calcularMinimoPagos_v2(String
	 * nombreGrupo) { List<Balance> balances = calcularBalance(nombreGrupo);
	 * balances.stream() .filter(balanceP -> balanceP.getImporte() > 0)
	 * .map(balanceP -> { balances.stream() .filter(balanceN ->
	 * balanceN.getImporte() <0) .map(balanceN -> { if(balanceN.getImporte() > 0 &&
	 * balanceP.getImporte() > 0) { return Balance.builder() .build(); } return
	 * null; }) .collect(Collectors.toList()); }) .collect(Collectors.toList());
	 * 
	 * return null; }
	 */
	
}
