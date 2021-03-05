package com.proyectosPersonales.springboot.app.gastos.dto;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name = "Pago")
public class PagoDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idPago;
	
	private Long importe; //importe del pago
	
	private String descripcion;//descripcion del pago
	
	private Calendar fecha;//fecha en la que se realizó el apgo
	
	private List<AmigoDTO> deudores; //lista de deudores
	
	private PersonaDTO pagador; //pagador del pago

}
