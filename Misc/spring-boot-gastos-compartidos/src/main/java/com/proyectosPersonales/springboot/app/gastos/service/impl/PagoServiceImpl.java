
package com.proyectosPersonales.springboot.app.gastos.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectosPersonales.springboot.app.gastos.dto.Balance;
import com.proyectosPersonales.springboot.app.gastos.dto.Deuda;
import com.proyectosPersonales.springboot.app.gastos.dto.Grupo;
import com.proyectosPersonales.springboot.app.gastos.dto.Pago;
import com.proyectosPersonales.springboot.app.gastos.dto.Usuario;
import com.proyectosPersonales.springboot.app.gastos.dto.UsuarioPago;
import com.proyectosPersonales.springboot.app.gastos.service.interfaces.BalanceService;
import com.proyectosPersonales.springboot.app.gastos.service.interfaces.GrupoService;
import com.proyectosPersonales.springboot.app.gastos.service.interfaces.PagoService;
import com.proyectosPersonales.springboot.app.gastos.service.interfaces.UsuarioService;

@Service
public class PagoServiceImpl implements PagoService {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private GrupoService grupoService;
	
	@Autowired
	private BalanceService balanceService;
	
	@Override
	public void añadirUsuarioPago(UsuarioPago usuarioPago) {
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
								.idPagador(usuario_db.getIdUsuario())
								.build();
						participante_db.getMisDeudas().add(deuda);
						usuarioService.guardarUsuario(participante_db);
					}			
				});
			}
			usuarioService.guardarUsuario(usuario_db);
		}
	}

	@Override
	public List<UsuarioPago> consultarPagosCompartidos(String nombreGrupo) {
		Grupo grupo_db = grupoService.buscarGrupo(nombreGrupo);
		if(grupo_db != null) {
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
		return null;
	}

	@Override
	public List<Balance> calcularBalance(String nombreGrupo) {
		Grupo grupo_db = grupoService.buscarGrupo(nombreGrupo);
		if(grupo_db != null) {
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
		return null;
	}
}
