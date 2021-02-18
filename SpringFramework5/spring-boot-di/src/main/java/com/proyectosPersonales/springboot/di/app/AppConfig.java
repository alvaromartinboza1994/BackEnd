package com.proyectosPersonales.springboot.di.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

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

}
