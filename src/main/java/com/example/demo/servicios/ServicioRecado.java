package com.example.demo.servicios;

import com.example.demo.modelo.Recado;
import com.example.demo.repositorios.RepositorioRecado;
import org.hibernate.annotations.Formula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioRecado{
    @Autowired
    RepositorioRecado repositorio;
    public List<Recado> findAll(){
        return repositorio.findAll();
    }

    public Recado save(Recado recado){
        repositorio.save(recado);
        return recado;
    }

    public Recado findById(long id){
        return repositorio.findById(id);
    }

}
