package com.proyectosPersonales.springboot.di.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.proyectosPersonales.springboot.di.app.models.service.MiServicio;

@Controller
public class indexController {
	
	private MiServicio servicio = new MiServicio(); //acoplamiento alto
	
	@GetMapping({"/", "", "/index"})
	public String index(Model model) {
		model.addAttribute("objeto", servicio.operacion());
		return "index";
	}

}
