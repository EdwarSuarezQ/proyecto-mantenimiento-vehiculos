package unipacifico.proyectoMttoVehiculos.dtos;
import java.util.Collection;
import java.util.Date;

import unipacifico.proyectoMttoVehiculos.models.Rol;
 

public class UsuariosDTO {
private String nombre;
private Long id;
    private String login;
 
    private String enabled;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private String email;
	private String password;
    private String apellido;

 private Collection<Rol> roles;


    public Collection<Rol> getRoles() {
        return this.roles;
    }

    public void setRoles(Collection<Rol> roles) {
        this.roles = roles;
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }


    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
 

    public String getEnabled() {
        return this.enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaModificacion() {
        return this.fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }



}
