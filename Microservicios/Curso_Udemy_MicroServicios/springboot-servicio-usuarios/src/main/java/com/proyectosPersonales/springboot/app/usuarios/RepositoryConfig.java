package com.proyectosPersonales.springboot.app.usuarios;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

import com.proyectosPersonales.springboot.app.usuarios.models.entity.Rol;
import com.proyectosPersonales.springboot.app.usuarios.models.entity.Usuario;

@Configuration //configuracion alternativa para obtener los ids en el json
public class RepositoryConfig implements RepositoryRestConfigurer{

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.exposeIdsFor(Usuario.class, Rol.class);
	}

	
}
