package unipacifico.proyectoMttoVehiculos.mappers;

import org.mapstruct.Mapper;

import unipacifico.proyectoMttoVehiculos.dtos.ProductoDTO;
import unipacifico.proyectoMttoVehiculos.models.Producto;

@Mapper(componentModel = "spring")
public interface ProductoMapper {

        ProductoDTO toProductoDTO(Producto producto);
        Producto toProducto(ProductoDTO productoDTO);
    
}
