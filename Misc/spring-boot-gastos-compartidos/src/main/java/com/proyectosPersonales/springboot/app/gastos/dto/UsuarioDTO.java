package com.proyectosPersonales.springboot.app.gastos.dto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name = "Usuario")
public class UsuarioDTO extends PersonaDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idUsuario;

	@Column(name = "amigos")
	private List<PersonaDTO> listaAmigos;

	private List<PagoDTO> pagosRealizados;

	private List<PagoDTO> deudas;

}
