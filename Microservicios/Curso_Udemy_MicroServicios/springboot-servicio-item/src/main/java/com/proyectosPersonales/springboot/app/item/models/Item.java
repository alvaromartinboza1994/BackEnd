package com.proyectosPersonales.springboot.app.item.models;

import com.proyectosPersonales.springboot.app.commons.models.entity.Producto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item {

	private Producto producto;
	private Integer cantidad;
	
	public Double getTotal() {
		return producto.getPrecio() * cantidad.doubleValue();
	}
	
}
