package unipacifico.proyectoMttoVehiculos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import unipacifico.proyectoMttoVehiculos.services.*;

@Controller
public class HomeController {

    @GetMapping("/")
    public String landing(Model model) {

        model.addAttribute("mensaje", "Bienvenido al Sistema de Mantenimiento de Vehículos");

        return "home";
    }
    
    @GetMapping("/home")
    public String home(){
        return "home";
    }


}