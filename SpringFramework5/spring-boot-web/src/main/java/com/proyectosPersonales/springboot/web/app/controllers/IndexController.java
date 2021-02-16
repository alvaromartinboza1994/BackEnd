package com.proyectosPersonales.springboot.web.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller //componente de spring de tipo controlador. Toda clase anotada con component, será un objeto manejado por el contenedor de spring framework automaticamente
//maneja las consultas del usuario
public class IndexController { 

	@GetMapping({"/index" , "/", "/home"})//tres rutas posibles para este metodo
	public String index() {
		return "index";
	}
}
