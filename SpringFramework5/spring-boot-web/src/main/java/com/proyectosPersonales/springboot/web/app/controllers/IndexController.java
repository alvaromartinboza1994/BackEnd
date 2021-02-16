package com.proyectosPersonales.springboot.web.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //componente de spring de tipo controlador. Toda clase anotada con component, será un objeto manejado por el contenedor de spring framework automaticamente
//maneja las consultas del usuario
public class IndexController { 

	@GetMapping({"/index" , "/", "/home"})//tres rutas posibles para este metodo
	public String index(Model model) {//model usa un mapa de java para asignar valores mediante llave y valor. Tambien vale con ModelMap, Map<String, Object> -> put y ModelAndView -> addObject (return ModelAndView)
		model.addAttribute("titulo", "hola Spring Framework!");
		return "index";
	}
}
