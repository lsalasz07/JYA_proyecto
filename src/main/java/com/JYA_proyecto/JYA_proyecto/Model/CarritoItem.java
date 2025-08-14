package com.JYA_proyecto.JYA_proyecto.model;

import java.math.BigDecimal;

public class CarritoItem {
    
    private Long productoId;
    private String nombre;
    private BigDecimal precio;
    private Integer cantidad;
    private String imagenUrl;
    
    // Constructores
    public CarritoItem() {}
    
    public CarritoItem(Long productoId, String nombre, BigDecimal precio, Integer cantidad, String imagenUrl) {
        this.productoId = productoId;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.imagenUrl = imagenUrl;
    }
    
    // Método para calcular subtotal
    public BigDecimal getSubtotal() {
        return precio.multiply(BigDecimal.valueOf(cantidad));
    }
    
    // Getters y Setters
    public Long getProductoId() { return productoId; }
    public void setProductoId(Long productoId) { this.productoId = productoId; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public BigDecimal getPrecio() { return precio; }
    public void setPrecio(BigDecimal precio) { this.precio = precio; }
    
    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
    
    public String getImagenUrl() { return imagenUrl; }
    public void setImagenUrl(String imagenUrl) { this.imagenUrl = imagenUrl; }
}