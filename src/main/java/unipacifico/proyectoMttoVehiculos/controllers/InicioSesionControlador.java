package unipacifico.proyectoMttoVehiculos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import unipacifico.proyectoMttoVehiculos.services.*;

@Controller
public class InicioSesionControlador {

	@Autowired
	private UsuariosService servicio;
	
	@GetMapping("/login")
	public String iniciarSesion() {
		return "login";
	}
	/*
	@GetMapping("/")
	public String verPaginaDeInicio(Model modelo) throws Exception {
		modelo.addAttribute("usuarios", servicio.getAllUsuarios());
		return "inicio/index2";
	}*/
}
