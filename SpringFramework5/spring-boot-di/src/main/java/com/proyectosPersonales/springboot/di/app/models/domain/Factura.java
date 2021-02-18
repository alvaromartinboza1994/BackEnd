package com.proyectosPersonales.springboot.di.app.models.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class Factura {

	@Value("${descripcion.factura}")
	private String descripcion;

	@Autowired
	private Cliente cliente;

	private List<ItemFactura> items;

}
