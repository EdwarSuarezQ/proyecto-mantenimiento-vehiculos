package unipacifico.proyectoMttoVehiculos.services;

import net.sf.jasperreports.engine.JRException;
import unipacifico.proyectoMttoVehiculos.dtos.ProductoDTO;
import unipacifico.proyectoMttoVehiculos.models.Producto;

import java.util.List;



public interface ProductoService {

    Producto saveProducto(Producto producto) throws Exception;

    Producto getProductoById(Long id) throws Exception;

    List<Producto> getAllProductos() throws Exception;

    Producto updateProducto(Producto producto) throws Exception;

    void deleteProducto(Long id) throws Exception;
    public byte[] generateProductoReport(List<Producto> productos)throws Exception;
    public byte[] generateProductoReportProcedimiento(String codigoProducto) throws Exception;
}
