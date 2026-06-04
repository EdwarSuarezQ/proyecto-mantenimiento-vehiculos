package unipacifico.proyectoMttoVehiculos.services;
import unipacifico.proyectoMttoVehiculos.models.Rol;
import unipacifico.proyectoMttoVehiculos.models.Usuarios;
import unipacifico.proyectoMttoVehiculos.repository.UsuariosRepository;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.User;
@Service
public class UsuariosServiceImpl implements UsuariosService {
    private final UsuariosRepository usuariosRepository;
    // @Autowired
    public UsuariosServiceImpl
        (UsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }
      /// @Override
      @Transactional(readOnly = true)
      public List<Usuarios> getAllUsuarios() throws Exception {
            List<Usuarios> usuarios = new ArrayList<Usuarios>();
            try {
            usuarios  = usuariosRepository.findAll();
        } catch (Exception e) {
                throw new Exception("No usuarioses found");
         
            } finally {
            }
          return usuarios;
      }
      
    // @Override
    @Transactional(readOnly = false, propagation 
    = Propagation.REQUIRED)
    public Usuarios saveUsuarios(Usuarios Usuarios) throws Exception {
        try {
            if (Usuarios == null) {
                throw new Exception("Usuarios is null");
            }
        } catch (Exception e) {
            throw new Exception("No usuarioses found");
     
        } finally {
        }
        return usuariosRepository.save(Usuarios);
    }

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuarios usuario = usuariosRepository.findByLogin(username);
		if(usuario == null) {
			throw new UsernameNotFoundException("Usuario o password inválidos");
		}
		return new User(usuario.getLogin(),usuario.getPassword(), mapearAutoridadesRoles(usuario.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Collection<Rol> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNombre())).collect(Collectors.toList());
	}

    

}