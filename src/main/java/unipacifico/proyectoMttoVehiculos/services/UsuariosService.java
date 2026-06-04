package unipacifico.proyectoMttoVehiculos.services;
import unipacifico.proyectoMttoVehiculos.models.Usuarios;
import unipacifico.proyectoMttoVehiculos.repository.UsuariosRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UsuariosService extends UserDetailsService {
 

    List<Usuarios> getAllUsuarios() throws Exception;
    
    Usuarios saveUsuarios(Usuarios usuarios) throws Exception;

  }
