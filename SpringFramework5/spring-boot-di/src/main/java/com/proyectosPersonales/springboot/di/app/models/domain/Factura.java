package com.proyectosPersonales.springboot.di.app.models.domain;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import lombok.Data;

@Data
@Component
@RequestScope //este bean durara lo que dure una peticion http del usuario
//@SessionScope //marca objeto como sesion -> carro de compra, sistema autenticacion.Persistente en la sesion http.Se destruye cuando cerremos el navegador, cuando ocurra un timeout o se invalide la sesion
//@ApplicationScope //o singleton
public class Factura implements Serializable{//si es sesion debe de ser serializable

	/**
	 * 
	 */
	private static final long serialVersionUID = 6995825952807717735L;//identificador de la serializacion

	@Value("${factura.descripcion}")
	private String descripcion;

	@Autowired
	private Cliente cliente;

	@Autowired
	//@Qualifier("itemsFacturaEquipamiento")
	private List<ItemFactura> items;
	
	@PostConstruct //se ejecuta justo despues de crear el objeto, despues de que se haya inyectado la dependencia
	public void inicializar() {
		cliente.setNombre(cliente.getNombre().concat(" ").concat("José"));
		descripcion = descripcion.concat(" del cliente: ").concat(cliente.getNombre());
	}
	
	//se ejecuta al parar el servidor del servicio
	//tras poner scope, se destruye cuando finaliza la peticion http
	@PreDestroy
	public void destruir() {
		System.out.println("Factura destruida".concat(descripcion));
	}

}
