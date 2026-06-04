package unipacifico.proyectoMttoVehiculos.services;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import unipacifico.proyectoMttoVehiculos.dtos.ProductoDTO;
import unipacifico.proyectoMttoVehiculos.models.Producto;
import unipacifico.proyectoMttoVehiculos.models.Usuarios;
import unipacifico.proyectoMttoVehiculos.repository.ProductoRepository;

import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private static final Logger logger = LoggerFactory.getLogger(ProductoService.class);

    //@Autowired
    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Producto saveProducto(Producto producto) throws Exception {
        if (producto == null) {
            throw new Exception("Producto is null");
        }
        return productoRepository.save(producto);
    }

    @Override
    @Transactional(readOnly = true)
    public Producto getProductoById(Long id) throws Exception {
        if (id == null) {
            throw new Exception("Id is null");
        }
        Producto producto = productoRepository.findById(id).orElseThrow(() -> new Exception("Producto not found with id: " + id));
        return producto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> getAllProductos() throws Exception {
        return Optional.ofNullable(productoRepository.findAll()).orElse(Collections.emptyList());
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Producto updateProducto(Producto producto) throws Exception{
        if (producto == null) {
            throw new Exception("Producto is null");
        }
        return productoRepository.save(producto);
    }

    @SuppressWarnings("null")
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteProducto(Long id) throws Exception{
        if (id == null) {
            throw new Exception("Id is null");
        }
        Producto producto = productoRepository.findById(id).orElseThrow(() -> new Exception("Producto not found with id: " + id));
        productoRepository.delete(producto);

    }

    public byte[] generateProductoReport(List<Producto> productos) throws JRException {
        // Cargar el archivo JRXML
        InputStream template = this.getClass().getResourceAsStream("/informes/ListadoProductos.jrxml");

        // Compilar el archivo JRXML
        JasperReport jasperReport = JasperCompileManager.compileReport(template);

        // Convertir la lista de productos en JRBeanCollectionDataSource
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(productos);

        // Parámetros adicionales (opcional)
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("ReportTitle", "Reporte de Productos");

        // Llenar el reporte con datos
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        // Exportar a PDF
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

        return outputStream.toByteArray();
    }
    
     
    @Transactional(readOnly = true)
    public byte[] generateProductoReportProcedimiento(String codigoProducto) {
        try {
            // 1. Obtener datos del procedimiento almacenado
            List<Producto> datosReporte = productoRepository.obtenerDatosReporte(codigoProducto);

            // 2. Validar los datos obtenidos
            if (datosReporte == null || datosReporte.isEmpty()) {
                logger.warn("El procedimiento almacenado no devolvió datos para el código: {}", codigoProducto);
                throw new IllegalArgumentException("No se encontraron datos para el producto con código: " + codigoProducto);
            }

            logger.debug("Datos obtenidos del procedimiento: {}", datosReporte);

            // 3. Cargar la plantilla JRXML
            InputStream template = this.getClass().getResourceAsStream("/informes/productos.jrxml");
            if (template == null) {
                logger.error("No se pudo encontrar la plantilla JRXML en la ruta especificada.");
                throw new RuntimeException("La plantilla del reporte no está disponible.");
            }

            // 4. Compilar la plantilla JRXML a JasperReport
            JasperReport jasperReport = JasperCompileManager.compileReport(template);

            // 5. Crear la fuente de datos
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(datosReporte);

            // 6. Configurar parámetros adicionales (si aplica)
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("ReportTitle", "Reporte del Producto");
            parameters.put("CodigoProducto", codigoProducto); // Ejemplo de parámetro opcional

            // 7. Llenar el reporte con datos y parámetros
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            // 8. Exportar el reporte a PDF
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

            logger.info("Reporte generado exitosamente para el código de producto: {}", codigoProducto);

            return outputStream.toByteArray();

        } catch (JRException e) {
            logger.error("Error al generar el reporte: {}", e.getMessage(), e);
            throw new RuntimeException("Ocurrió un error al generar el reporte", e);
        } catch (Exception e) {
            logger.error("Error inesperado: {}", e.getMessage(), e);
            throw new RuntimeException("Error inesperado al generar el reporte", e);
        }
    }
     

}