
package com.proyectosPersonales.springboot.app.gastos.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
import com.proyectosPersonales.springboot.app.gastos.service.interfaces.DeudaService;
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

	@Override
	public List<UsuarioDeuda> calcularMinimoPagos(String nombreGrupo) {
		Grupo grupo_db = grupoService.buscarGrupo(nombreGrupo);
		if(grupo_db != null) {
			grupo_db.getParticipantes().forEach(participante -> {
				Grupo grupo_db_local = grupoService.buscarGrupo(nombreGrupo);
				grupo_db_local.getParticipantes().forEach(compi -> {
					if(!compi.getIdUsuario().equals(participante.getIdUsuario())) {
						//1)comprobar si tengo deudas con el usuario i
						Double miDeudaContigo = participante.getMisDeudas().stream()
								.filter(deuda -> compi.getIdUsuario().equals(deuda.getIdPagador()))
								.map(Deuda::getImporte)
								.collect(Collectors.summingDouble(Double::doubleValue));
						if(miDeudaContigo > 0) {
							//2)comprobar si el usuario i tiene deudas conmigo 
							Double tuDeudaConmigo = compi.getMisDeudas().stream()
									.filter(deuda -> participante.getIdUsuario().equals(deuda.getIdPagador()))
									.map(Deuda::getImporte)
									.collect(Collectors.summingDouble(Double::doubleValue));
							if(miDeudaContigo > tuDeudaConmigo) {
								//3)eliminar mis deudas antiguas con compi y hacer una sola actualizada
								List<Deuda> deudasEliminar = participante.getMisDeudas().stream()
									.filter(deuda -> compi.getIdUsuario().equals(deuda.getIdPagador()))
									.collect(Collectors.toList());
								participante.getMisDeudas().removeAll(deudasEliminar);
								Calendar calendar = Calendar.getInstance();
								calendar.setTime(new Date());
								participante.getMisDeudas().add(Deuda.builder()
										.descripcion("Deuda Actualiza")
										.importe(miDeudaContigo - tuDeudaConmigo)
										.fecha(calendar)
										.idPagador(compi.getIdUsuario())
										.build());
								usuarioService.guardarUsuario(participante);
								//4)comprobar deudas asumibles
								//5)asumimos primero las deudas mas pequeñas para reducir movimientos
								compi.getMisDeudas().stream().sorted(Comparator.comparing(Deuda::getImporte));
								compi.getMisDeudas().forEach(deuda -> {
									Deuda miDeuda = participante.getMisDeudas().stream()
											.filter(deuda_local -> compi.getIdUsuario().equals(deuda_local.getIdPagador()))
											.findFirst().orElse(Deuda.builder().build());
									if(miDeuda.getImporte() != null && deuda.getImporte() < miDeuda.getImporte()) {
										participante.getMisDeudas().remove(miDeuda);
										compi.getMisDeudas().remove(deuda);
										miDeuda.setImporte(miDeuda.getImporte() - deuda.getImporte());
										participante.getMisDeudas().add(miDeuda);
										usuarioService.guardarUsuario(participante);
										usuarioService.guardarUsuario(compi);
									}
								});
							}
						}
					}
				});
			});
		}
		grupo_db = grupoService.buscarGrupo(nombreGrupo);
		List<UsuarioDeuda> usuariosDeudas = new ArrayList<>();
		grupo_db.getParticipantes().forEach(participante -> {
			usuariosDeudas.add(UsuarioDeuda.builder()
					.deudas(participante.getMisDeudas())
					.codUsuario(participante.getCodUsuario())
					.build());
		});
		return usuariosDeudas;
	}
	
}
