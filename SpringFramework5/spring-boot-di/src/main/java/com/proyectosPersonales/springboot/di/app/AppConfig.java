package com.proyectosPersonales.springboot.di.app;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.proyectosPersonales.springboot.di.app.models.domain.ItemFactura;
import com.proyectosPersonales.springboot.di.app.models.domain.Producto;
import com.proyectosPersonales.springboot.di.app.models.service.IServicio;
import com.proyectosPersonales.springboot.di.app.models.service.MiServicio;
import com.proyectosPersonales.springboot.di.app.models.service.MiServicioComplejo;

@Configuration
public class AppConfig {
	
	//metodos para crear componentes y retornan la instancia del objeto
	@Bean("miServicioSimple")
	@Primary
	public IServicio registrarMiServicio() {
		return new MiServicio();
	}
	
	@Bean("miServicioComplejo")	
	public IServicio registrarMiServicioComplejo() {
		return new MiServicioComplejo();
	}
	
	@Bean("itemsFactura")
	public List<ItemFactura> registrarItems() {
		Producto producto1 = Producto.builder().nombre("Zapatillas Nike").precio(100).build();
		Producto producto2 = Producto.builder().nombre("Zapatillas Adidas").precio(200).build();
		
		ItemFactura linea1 = ItemFactura.builder().producto(producto1).cantidad(2).build();
		ItemFactura linea2 = ItemFactura.builder().producto(producto2).cantidad(4).build();
		
		return Arrays.asList(linea1, linea2);
	}
	
	@Bean("itemsFacturaEquipamiento")
	@Primary
	public List<ItemFactura> registrarItemsOficina() {
		Producto producto1 = Producto.builder().nombre("Bolsa de deporte Acliclas").precio(15).build();
		Producto producto2 = Producto.builder().nombre("Chandal Mike").precio(50).build();
		Producto producto3 = Producto.builder().nombre("Gorra Kampion").precio(20).build();
		Producto producto4 = Producto.builder().nombre("Sudadera Jeebok").precio(40).build();
		
		ItemFactura linea1 = ItemFactura.builder().producto(producto1).cantidad(2).build();
		ItemFactura linea2 = ItemFactura.builder().producto(producto2).cantidad(4).build();
		ItemFactura linea3 = ItemFactura.builder().producto(producto3).cantidad(1).build();
		ItemFactura linea4 = ItemFactura.builder().producto(producto4).cantidad(3).build();
		
		return Arrays.asList(linea1, linea2, linea3, linea4);
	}

}
