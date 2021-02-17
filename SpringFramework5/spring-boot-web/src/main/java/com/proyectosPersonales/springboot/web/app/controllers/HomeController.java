package com.proyectosPersonales.springboot.web.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String home() {
		//return "redirect:/app/index"; //redirect es palabra clave
		
		return "forward:/app/index"; //no modifica la ruta url. mejor opciomn para pagina de inicio
	}
}
