package com.example.demo.servicios;

import com.example.demo.modelo.Autor;
import com.example.demo.repositorios.RepositorioAutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ServicioAutor {
    @Autowired
    private RepositorioAutor repositorio;

    public Autor save(Autor e) {
        repositorio.save(e);
        return e;
    }

    public List<Autor> findAll() {
        return (List<Autor>) repositorio.findAll();
    }

    public Autor findById(long id) {
        return repositorio.findById(id).get();
    }

    public Autor edit(Autor e) {
        repositorio.save(e);
        return e;
    }

    public void deleteAutor(long id) {
        repositorio.deleteById(id);
    }

    public Autor findByNombre(String nombre){
        return repositorio.findByNombre(nombre);
    }

    public List<Autor> findByNombreContains(String nombre){
        return  repositorio.findByNombreContains(nombre);
    }
}
