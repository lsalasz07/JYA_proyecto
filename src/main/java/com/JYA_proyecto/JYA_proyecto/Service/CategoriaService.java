package com.JYA_proyecto.JYA_proyecto.service;

import com.JYA_proyecto.JYA_proyecto.dao.CategoriaDao;
import com.JYA_proyecto.JYA_proyecto.model.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaDao categoriaDao;

    public List<Categoria> listarTodas() {
        return categoriaDao.findAll();
    }

    public Categoria buscarPorNombre(String nombre) {
        return categoriaDao.findByNombre(nombre);
    }
}
