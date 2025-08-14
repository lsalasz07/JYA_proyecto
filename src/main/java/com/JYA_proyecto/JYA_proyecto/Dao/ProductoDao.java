package com.JYA_proyecto.JYA_proyecto.dao;

import com.JYA_proyecto.JYA_proyecto.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductoDao extends JpaRepository<Producto, Long> {
    
    // Buscar todos los productos de una categoría por nombre
    List<Producto> findByCategoriaNombre(String nombre);

    // Buscar productos destacados
    List<Producto> findByDestacadoTrue();

    // Buscar productos activos
    List<Producto> findByActivoTrue();
}
