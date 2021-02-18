package com.proyectosPersonales.springboot.di.app.models.service;

import org.springframework.stereotype.Component;

@Component("miServicioComplejo")
//@Primary //candidato por defecto a inyectar en las clases que lo usen
public class MiServicioComplejo implements IServicio {
	
	@Override
	public String operacion() {
		return "ejecutando algún proceso importante complicado";
	}

}
