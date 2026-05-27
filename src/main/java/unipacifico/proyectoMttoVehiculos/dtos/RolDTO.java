package unipacifico.proyectoMttoVehiculos.dtos;
import java.util.Collection;
import java.util.Date;

import unipacifico.proyectoMttoVehiculos.models.Rol;
 

public class RolDTO {
 
private Long id;
private String nombre;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


}
