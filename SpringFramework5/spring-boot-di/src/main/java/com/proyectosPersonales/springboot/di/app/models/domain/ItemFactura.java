package com.proyectosPersonales.springboot.di.app.models.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ItemFactura {
	
	private Producto producto;
	private Integer cantidad;

}
