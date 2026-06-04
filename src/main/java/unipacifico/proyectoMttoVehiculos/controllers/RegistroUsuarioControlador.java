package unipacifico.proyectoMttoVehiculos.controllers;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import unipacifico.proyectoMttoVehiculos.dtos.UsuariosDTO;
import unipacifico.proyectoMttoVehiculos.mappers.UsuariosMapper;
import unipacifico.proyectoMttoVehiculos.models.Usuarios;
import unipacifico.proyectoMttoVehiculos.services.UsuariosService;
@Controller
@RequestMapping("/registro")
public class RegistroUsuarioControlador {

	private UsuariosService usuarioServicio;
    private  UsuariosMapper UsuariosMapper;
	public RegistroUsuarioControlador(UsuariosService usuarioServicio, UsuariosMapper UsuariosMapper)
        {
		super();
		this.usuarioServicio = usuarioServicio;
		this.UsuariosMapper = UsuariosMapper;
	}
	
	@ModelAttribute("usuario")
	public UsuariosDTO retornarNuevoUsuarioRegistroDTO() {
		return new UsuariosDTO();
	}

	@GetMapping
	public String mostrarFormularioDeRegistro() {
		return "registro";
	}
	
	@PostMapping
	public String registrarCuentaDeUsuario(@Valid @ModelAttribute("usuariosDto") UsuariosDTO UsuariosDto, BindingResult result)
	throws Exception {

		if (result.hasErrors()) {
			return "login";
		}

		Usuarios usuarios = UsuariosMapper.toUsuarios(UsuariosDto);
		usuarios.setEnabled("true");
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		usuarios.setPassword(passwordEncoder.encode(UsuariosDto.getPassword()));
		usuarioServicio.saveUsuarios(usuarios);
		return "redirect:/registro?exito";
		}
}
