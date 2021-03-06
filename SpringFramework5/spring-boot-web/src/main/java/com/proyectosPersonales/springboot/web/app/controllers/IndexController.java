package com.proyectosPersonales.springboot.web.app.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proyectosPersonales.springboot.web.app.models.Usuario;

@Controller //componente de spring de tipo controlador. Toda clase anotada con component, será un objeto manejado por el contenedor de spring framework automaticamente
//maneja las consultas del usuario
@RequestMapping("/app")//ruta de primer nivel generica para todos los metodos del controlador
public class IndexController { 
	
	@Value("${texto.indexcontroller.index.titulo}")
	private String textoIndex;
	
	@Value("${texto.indexcontroller.perfil.titulo}")
	private String textoPerfil;
	
	@Value("${texto.indexcontroller.listar.titulo}")
	private String textoListar;

	@GetMapping({"/index" , "/", "/home"})//tres rutas posibles para este metodo
	public String index(Model model) {//model usa un mapa de java para asignar valores mediante llave y valor. Tambien vale con ModelMap, Map<String, Object> -> put y ModelAndView -> addObject (return ModelAndView)
		model.addAttribute("titulo", textoIndex);
		return "index";
	}
	
	@RequestMapping("/perfil")
	public String perfil(Model model) {
		Usuario usuario = Usuario.builder().nombre("Alvaro").apellido("Martin").email("micorreo@gmail.com").build();
		model.addAttribute("usuario", usuario);
		model.addAttribute("titulo", textoPerfil.concat(usuario.getNombre()));
		return "perfil";
	}
	
	@RequestMapping("/listar")
	public String listar(Model model) {
		model.addAttribute("titulo", textoListar);
		return "listar";
	}
	
	@ModelAttribute("usuarios")//es comun a todos los metodos del controlador, lo usamos cuando queremos pasar datos a la vista que son comunes a varios metodos handler del controlador o cuando es un formulario para poblar los campos
	public List<Usuario> poblarUsuarios() {
		List<Usuario> usuarios = Arrays.asList(Usuario.builder().nombre("Alvaro").apellido("Martin").email("micorreo@gmail.com").build(),
				Usuario.builder().nombre("Paca").apellido("Hains").email("micorreo2@gmail.com").build(),
				Usuario.builder().nombre("Hipo").apellido("Potamo").email("micorreo3@gmail.com").build(),
				Usuario.builder().nombre("Taza").apellido("Te").email("micorreo4@gmail.com").build());
		return usuarios;
	}
}
