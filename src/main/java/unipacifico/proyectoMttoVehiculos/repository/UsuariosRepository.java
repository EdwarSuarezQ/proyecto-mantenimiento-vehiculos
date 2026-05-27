package unipacifico.proyectoMttoVehiculos.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import unipacifico.proyectoMttoVehiculos.models.Usuarios; 

public interface UsuariosRepository extends 
            JpaRepository<Usuarios, Long>{

              public Usuarios findByEmail(String email);
              public Usuarios findByLogin(String login);
}
