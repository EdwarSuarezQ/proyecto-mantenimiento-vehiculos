package unipacifico.proyectoMttoVehiculos.mappers;

import org.mapstruct.Mapper;

import unipacifico.proyectoMttoVehiculos.dtos.RolDTO;
import unipacifico.proyectoMttoVehiculos.models.Rol;

@Mapper(componentModel = "spring")
public interface RolMapper {


    RolDTO toRolDTO(Rol rol);

    Rol toRol(RolDTO rolDTO);
}
