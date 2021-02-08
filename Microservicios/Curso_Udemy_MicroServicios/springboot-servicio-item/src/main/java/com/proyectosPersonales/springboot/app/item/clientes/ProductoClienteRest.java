package com.proyectosPersonales.springboot.app.item.clientes;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.proyectosPersonales.springboot.app.item.models.Producto;

@FeignClient(name = "servicio-productos") //indicamos que esta interfaz es un cliente Feign
public interface ProductoClienteRest {

	@GetMapping("/listar") //misma ruta que el servico al que queremos conectar
	public List<Producto> listar();
	
	
	@GetMapping("/ver/{id}")
	public Producto detalle(@PathVariable Long id);
}
