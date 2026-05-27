
package unipacifico.proyectoMttoVehiculos.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProductoDTO {

    private Long productoId;

    @NotBlank(message = "El código no puede estar vacío")
    private String codigo;

    //@NotBlank(message = "El estado no puede estar vacío")
    private String estado;
  
    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;
    
    @NotBlank(message = "La descripción no puede estar vacía")
    @Size(max = 500, message = "La descripción no puede tener más de 500 caracteres")
    private String descripcion;

    @NotBlank(message = "La observación no puede estar vacía")
    @Size(max = 500, message = "La observación no puede tener más de 500 caracteres")
    private String observacion;
    
    @NotNull(message = "El saldo mínimo no puede estar vacío")
    @Min(value = 0, message = "El saldo mínimo no puede ser negativo")
    private Double saldoMinimo;
    
    @NotNull(message = "El saldo máximo no puede estar vacío")
    @Min(value = 0, message = "El saldo máximo no puede ser negativo")
    private Double saldoMaximo;

    //@NotNull(message = "El tipo de producto no puede ser nulo")
    private Long tipoProdId;

    //@NotNull(message = "La unidad de medida no puede ser nula")
    private Long udadMedProd;

    //@NotNull(message = "El almacén no puede ser nulo")
    private Long almacenId;
    
    @NotNull(message = "El precio no puede estar vacío")
    @Min(value = 0, message = "El precio no puede ser negativo")
    private Double precio;

    @NotNull(message = "La fecha de creación no puede ser nula")
    private String fechaCreacion;



    public Long getProductoId() {
        return this.productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObservacion() {
        return this.observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Double getSaldoMinimo() {
        return this.saldoMinimo;
    }

    public void setSaldoMinimo(Double saldoMinimo) {
        this.saldoMinimo = saldoMinimo;
    }

    public Double getSaldoMaximo() {
        return this.saldoMaximo;
    }

    public void setSaldoMaximo(Double saldoMaximo) {
        this.saldoMaximo = saldoMaximo;
    }

    public Long getTipoProdId() {
        return this.tipoProdId;
    }

    public void setTipoProdId(Long tipoProdId) {
        this.tipoProdId = tipoProdId;
    }

    public Long getUdadMedProd() {
        return this.udadMedProd;
    }

    public void setUdadMedProd(Long udadMedProd) {
        this.udadMedProd = udadMedProd;
    }

    public Long getAlmacenId() {
        return this.almacenId;
    }

    public void setAlmacenId(Long almacenId) {
        this.almacenId = almacenId;
    }

    public Double getPrecio() {
        return this.precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getFechaCreacion() {
        return this.fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    
}
