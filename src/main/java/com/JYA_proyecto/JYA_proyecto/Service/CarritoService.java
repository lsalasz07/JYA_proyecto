package com.JYA_proyecto.JYA_proyecto.service;

import com.JYA_proyecto.JYA_proyecto.model.CarritoItem;
import com.JYA_proyecto.JYA_proyecto.model.Producto;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@SessionScope
public class CarritoService {
    
    private List<CarritoItem> items = new ArrayList<>();
    
    public void agregarItem(Producto producto, Integer cantidad) {
        Optional<CarritoItem> itemExistente = items.stream()
            .filter(item -> item.getProductoId().equals(producto.getId()))
            .findFirst();
            
        if (itemExistente.isPresent()) {
            // Si el producto ya existe, actualizar cantidad
            CarritoItem item = itemExistente.get();
            item.setCantidad(item.getCantidad() + cantidad);
        } else {
            // Si es nuevo, agregar al carrito
            CarritoItem nuevoItem = new CarritoItem(
                producto.getId(),
                producto.getNombre(),
                producto.getPrecio(),
                cantidad,
                producto.getImagenUrl()
            );
            items.add(nuevoItem);
        }
    }
    
    public void actualizarCantidad(Long productoId, Integer cantidad) {
        items.stream()
            .filter(item -> item.getProductoId().equals(productoId))
            .findFirst()
            .ifPresent(item -> item.setCantidad(cantidad));
    }
    
    public void eliminarItem(Long productoId) {
        items.removeIf(item -> item.getProductoId().equals(productoId));
    }
    
    public List<CarritoItem> obtenerItems() {
        return new ArrayList<>(items);
    }
    
    public BigDecimal calcularTotal() {
        return items.stream()
            .map(CarritoItem::getSubtotal)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    
    public Integer obtenerCantidadTotal() {
        return items.stream()
            .mapToInt(CarritoItem::getCantidad)
            .sum();
    }
    
    public void limpiarCarrito() {
        items.clear();
    }
    
    public boolean estaVacio() {
        return items.isEmpty();
    }
}