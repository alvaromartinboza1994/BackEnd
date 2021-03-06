package com.proyectosPersonales.springboot.app.gastos.dto;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name = "Pago")
public class Pago implements Serializable {

	private static final long serialVersionUID = 1048110091610768887L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idPago;

	private Long importe; // importe del pago

	private String descripcion;// descripcion del pago

	private Calendar fecha;// fecha en la que se realizó el apgo

	/*
	 * @ManyToOne(fetch = FetchType.LAZY, optional = false)
	 * 
	 * @JoinColumns({ @JoinColumn(name = "idUsuario", referencedColumnName =
	 * "idUsuario"),
	 * 
	 * @JoinColumn(name = "nombre", referencedColumnName = "nombre"),
	 * 
	 * @JoinColumn(name = "apellidos", referencedColumnName = "apellidos") })
	 * private Usuario pagador;
	 * 
	 * @ManyToMany(mappedBy = "misDeudas")
	 * 
	 * @Column(name = "deudores") private List<Usuario> listaDeudores;
	 */
}
