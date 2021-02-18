package com.proyectosPersonales.springboot.di.app.models.domain;

import lombok.Data;

@Data
public class ItemFactura {
	
	private Producto producto;
	private Integer cantidad;

}
