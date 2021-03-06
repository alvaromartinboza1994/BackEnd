package com.proyectosPersonales.springboot.app.item.models.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.proyectosPersonales.springboot.app.item.clientes.ProductoClienteRest;
import com.proyectosPersonales.springboot.app.item.models.Item;
import com.proyectosPersonales.springboot.app.commons.models.entity.Producto;

@Service("serviceFeign")
//@Primary //se inyecta por defecto en el controlador en autowired
public class ItemServiceFeign implements ItemService {
	
	@Autowired
	private ProductoClienteRest clienteFeign;

	@Override
	public List<Item> findAll() {
		return clienteFeign.listar().stream().map(producto -> Item.builder().producto(producto).cantidad(1).build())
				.collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		return Item.builder().producto(clienteFeign.detalle(id)).cantidad(cantidad).build();
	}

	@Override
	public Producto save(Producto producto) {
		return clienteFeign.crear(producto);
	}

	@Override
	public Producto update(Producto producto, Long id) {
		return clienteFeign.editar(producto, id);
	}

	@Override
	public void delete(Long id) {
		clienteFeign.eliminar(id);		
	}

}
