package com.proyectosPersonales.springboot.app.gastos.dto;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pago {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Long importe; // importe del pago

	private String descripcion;// descripcion del pago

	private Calendar fecha;// fecha en la que se realizó el pago

	/*
	 * @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	 * 
	 * @JoinTable(name = "usuario_pago", joinColumns = { @JoinColumn(name = "id")},
	 * inverseJoinColumns = {
	 * 
	 * @JoinColumn(name = "pago_id") }) private List<Usuario> deudores;
	 */

}
