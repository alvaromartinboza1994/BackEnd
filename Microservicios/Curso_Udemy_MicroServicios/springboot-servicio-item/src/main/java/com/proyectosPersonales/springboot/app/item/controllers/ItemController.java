package com.proyectosPersonales.springboot.app.item.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.proyectosPersonales.springboot.app.item.models.Item;
import com.proyectosPersonales.springboot.app.item.models.Producto;
import com.proyectosPersonales.springboot.app.item.models.service.ItemService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ItemController {
	
	@Autowired
	private Environment env;

	@Autowired
	@Qualifier("serviceFeign") //equivalente a poner primary en el service
	private ItemService itemService;
	
	@Value("${configuracion.texto}") //inyección de dependencias, spring incluye el valor de la configuración en la variable
	private String texto;
	
	
	@GetMapping("/listar")
	public List<Item> listar() {
		return itemService.findAll();
	}
	
	@HystrixCommand(fallbackMethod = "metodoAlternativo")
	@GetMapping("/ver/{id}/cantidad/{cantidad}")
	public Item detalle(@PathVariable Long id, @PathVariable Integer cantidad) {
		return itemService.findById(id, cantidad);
	}
	
	public Item metodoAlternativo(Long id, Integer cantidad) {
		return Item.builder().cantidad(cantidad).producto(Producto.builder().nombre("Cámara Sony").precio(500.00).id(id).build()).build();
	}
	
	@GetMapping("/obtener-config")
	public ResponseEntity<?> obtenerConfiguracion(@Value("${server.port}") String puerto) {
		log.info(texto);
		Map<String, String> json = new HashMap<>();
		json.put("texto", texto);
		json.put("puerto", puerto);
		if(env.getActiveProfiles().length > 0 && env.getActiveProfiles()[0].equals("dev")) {
			json.put("autor.nombre", env.getProperty("configuracion.autor.nombre"));
			json.put("autor.email", env.getProperty("configuracion.autor.email"));
		}
		return new ResponseEntity<Map<String, String>>(json, HttpStatus.OK);
	}
	
}
