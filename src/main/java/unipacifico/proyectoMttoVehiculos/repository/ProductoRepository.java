package unipacifico.proyectoMttoVehiculos.repository;
import unipacifico.proyectoMttoVehiculos.dtos.*;
import unipacifico.proyectoMttoVehiculos.models.Producto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface ProductoRepository extends JpaRepository<Producto, Long>{

    @Procedure(procedureName = "listado_productos")
    List<Producto> obtenerDatosReporte(String codigoProducto);
}