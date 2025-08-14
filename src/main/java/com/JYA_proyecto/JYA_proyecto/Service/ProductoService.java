package com.JYA_proyecto.JYA_proyecto.service;

import com.JYA_proyecto.JYA_proyecto.dao.ProductoDao;
import com.JYA_proyecto.JYA_proyecto.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoDao productoDao;

    public List<Producto> obtenerTodos() {
        return productoDao.findByActivoTrue(); // Solo productos activos
    }

    public Optional<Producto> obtenerPorId(Long id) {
        return productoDao.findById(id)
                .filter(Producto::getActivo); // Solo si esta activo
    }

    public List<Producto> obtenerDestacados() {
        return productoDao.findByDestacadoTrue(); // Productos marcados como destacados
    }

    public List<Producto> obtenerPorCategoria(String nombreCategoria) {
        return productoDao.findByCategoriaNombre(nombreCategoria); // Filtrar por categoria
    }

    public boolean reducirStock(Long productoId, Integer cantidad) {
        Optional<Producto> productoOpt = productoDao.findById(productoId);
        if (productoOpt.isPresent()) {
            Producto producto = productoOpt.get();
            if (producto.getStock() >= cantidad) {
                producto.setStock(producto.getStock() - cantidad);
                productoDao.save(producto); // Actualizar en la BD
                return true;
            }
        }
        return false;
    }
}
