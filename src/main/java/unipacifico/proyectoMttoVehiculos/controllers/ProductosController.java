package unipacifico.proyectoMttoVehiculos.controllers;

import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import jakarta.validation.Valid;
import unipacifico.proyectoMttoVehiculos.dtos.ProductoDTO;
import unipacifico.proyectoMttoVehiculos.mappers.ProductoMapper;
import unipacifico.proyectoMttoVehiculos.models.Producto;
import unipacifico.proyectoMttoVehiculos.services.ProductoService;

import java.io.ByteArrayOutputStream;
import org.springframework.http.HttpHeaders;

/** trabajar con procedimientos almacenados**/
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/productos")
public class ProductosController {

    private static final Logger log = LoggerFactory.getLogger(ProductosController.class);

    private final ProductoService productoService;
    private final ProductoMapper productoMapper;
    
    public ProductosController(ProductoService productoService, ProductoMapper productoMapper) {
        this.productoService = productoService;
        this.productoMapper = productoMapper;
    }

    @GetMapping({"", "/"})
    public String listProductos(Model model) throws Exception {
        List<Producto> producto = productoService.getAllProductos();
        log.info("productos: {}", producto);
        if (producto.isEmpty()) {
            model.addAttribute("clase", "danger");
            model.addAttribute("mensaje", "No existen productos");
            model.addAttribute("productos", new ArrayList<Producto>());
        } else {
            model.addAttribute("productos", producto);
        }
        return "productos/index";
    }

    @GetMapping("/crear")
    public String mostrarPaginaCrearProducto(Model model) {
        ProductoDTO productoDto = new ProductoDTO();
        model.addAttribute("productoDto", productoDto);
        return "productos/crearProducto";
    }

    @PostMapping("/crear")
    public String addProducto(@Valid @ModelAttribute("productoDto") ProductoDTO productoDto, BindingResult result, Model model) throws Exception {
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate fecha = LocalDate.parse(productoDto.getFechaCreacion(), formatter);
       
        //productoDto.setFechaCreacion(LocalDate.now().toString());
        productoDto.setTipoProdId(1L);
        productoDto.setUdadMedProd(1L);
        productoDto.setAlmacenId(1L);
        productoDto.setEstado("A");

        if (result.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(err -> {
                errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
            });
            model.addAttribute("errores", errores);
            model.addAttribute("productoDto", productoDto);
            return "productos/crearProducto";
        }

        Producto producto = productoMapper.toProducto(productoDto);
        productoService.saveProducto(producto);

        model.addAttribute("clase", "success");
        model.addAttribute("mensaje", "El producto se ha creado correctamente");
                
        return "productos/crearProducto";
    }

    @GetMapping("/editar/{id}")
    public String mostrarPaginaEditarProducto(@PathVariable("id") Long id,  Model model) throws Exception {
        ProductoDTO productoDto = new ProductoDTO();
        Producto producto = productoService.getProductoById(id);
        productoDto = productoMapper.toProductoDTO(producto);
        model.addAttribute("productoDto", productoDto);
        return "productos/editarProducto";
    }

    @PostMapping("/editar/{id}")
    public String updateProducto(
            @Valid @ModelAttribute("productoDto") ProductoDTO productoDto,
            BindingResult result,
            Model model,
            RedirectAttributes flash,
            @PathVariable("id") Long id) throws Exception {

        if (result.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(err -> {
                errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
            });

            model.addAttribute("errores", errores);
            model.addAttribute("productoDto", productoDto);

            return "productos/editarProducto";
        }

        Producto producto = productoMapper.toProducto(productoDto);
        productoService.updateProducto(producto);

        model.addAttribute("clase", "success");
        model.addAttribute("mensaje", "Producto actualizado correctamente");

        return "productos/editarProducto";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable("id") Long id,  Model model){
        try {
            productoService.deleteProducto(id);
            model.addAttribute("clase", "success");
            model.addAttribute("mensaje", "Producto se ha eliminado correctamente");
        } catch (Exception e) {
            model.addAttribute("clase", "danger");
            model.addAttribute("mensaje", "Producto no se ha eliminado correctamente");
        }

        return "redirect:/productos/";
        
    }


     @GetMapping("/exportarExcel")
    public ResponseEntity<byte[]> exportToExcel() throws IOException {
        HttpHeaders headers = new HttpHeaders();
        byte[] excelData =null;
        try{
        List<Producto> producto = productoService.getAllProductos();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Productos");

        // Crear la fila de encabezado
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Codigo");
        headerRow.createCell(1).setCellValue("Nombre");
        headerRow.createCell(2).setCellValue("Descripción");
        headerRow.createCell(3).setCellValue("Precio");
        headerRow.createCell(4).setCellValue("productoId");

        // Rellenar las filas con los datos de los productos
        int rowNum = 1;
        for (Producto prod : producto) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(prod.getCodigo());
            row.createCell(1).setCellValue(prod.getNombre());
            row.createCell(2).setCellValue(prod.getDescripcion());
            row.createCell(3).setCellValue(prod.getPrecio());
            row.createCell(4).setCellValue(prod.getProductoId());
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        excelData = outputStream.toByteArray();

        
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "wwwproductos.xlsx");

     
        }catch (Exception e) {
        log.error(e.getMessage(), e);
       
        } 
        return ResponseEntity
        .ok()
        .headers(headers)
        .body(excelData);
    }


    @GetMapping("/exportarPDF")
    public ResponseEntity<byte[]> generateProductosReport() {
        try {
            List<Producto> productos = productoService.getAllProductos(); // Obtener productos
            byte[] pdf = productoService.generateProductoReport(productos);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=productos.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdf);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    @GetMapping("/exportarPDFVer2")
    public ResponseEntity<byte[]> generateProductoReportProcedimiento(@RequestParam String codigoProducto) {
        try {
            byte[] pdf = productoService.generateProductoReportProcedimiento(codigoProducto);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=productos.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdf);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    

}
