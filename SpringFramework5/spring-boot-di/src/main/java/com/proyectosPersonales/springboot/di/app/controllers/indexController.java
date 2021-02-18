package com.proyectosPersonales.springboot.di.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.proyectosPersonales.springboot.di.app.models.service.MiServicio;

@Controller
public class indexController {
	
	@Autowired //busca en el contenedor de Spring algun objeto de tipo MiServicio
	private MiServicio servicio;
	
	//private MiServicio servicio = new MiServicio(); //acoplamiento alto
	
	@GetMapping({"/", "", "/index"})
	public String index(Model model) {
		model.addAttribute("objeto", servicio.operacion());
		return "index";
	}

}
