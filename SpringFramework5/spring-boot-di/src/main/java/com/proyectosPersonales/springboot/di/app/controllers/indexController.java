package com.proyectosPersonales.springboot.di.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.proyectosPersonales.springboot.di.app.models.service.IServicio;

@Controller
public class indexController {

	@Autowired // busca en el contenedor de Spring algun objeto de tipo MiServicio
	@Qualifier("miServicioComplejo")//concretamos que servicio queremos inyectar
	private IServicio servicio;

	// private MiServicio servicio = new MiServicio(); //acoplamiento alto

	@GetMapping({ "/", "", "/index" })
	public String index(Model model) {
		model.addAttribute("objeto", servicio.operacion());
		return "index";
	}

	/*
	 * @Autowired public indexController(IServicio servicio) { super();
	 * this.servicio = servicio; }
	 * 
	 * @Autowired public void setServicio(IServicio servicio) { this.servicio =
	 * servicio; }
	 */

}
