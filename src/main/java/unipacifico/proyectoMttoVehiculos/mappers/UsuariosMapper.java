package unipacifico.proyectoMttoVehiculos.mappers;

import org.mapstruct.Mapper;

import unipacifico.proyectoMttoVehiculos.dtos.UsuariosDTO;
import unipacifico.proyectoMttoVehiculos.models.Usuarios;

@Mapper(componentModel = "spring")
public interface UsuariosMapper {


    UsuariosDTO toUsuariosDTO(Usuarios usuarios);

    Usuarios toUsuarios(UsuariosDTO usuariosDTO);
}
