package com.proyectosPersonales.springboot.app.gastos.dto;

import java.util.Calendar;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PagoDTO {
	
	private Long importe;
	
	private String descripcion;
	
	private Calendar fecha;
	
	private PersonaDTO pagador;
	
	private List<AmigoDTO> deudores;

}
