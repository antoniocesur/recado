package com.example.demo.servicios;

import com.example.demo.modelo.Autor;
import com.example.demo.modelo.MeGusta;
import com.example.demo.modelo.Recado;
import com.example.demo.repositorios.RepositorioMeGusta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioMeGusta {
    @Autowired
    RepositorioMeGusta repositorioMeGusta;
    public MeGusta findById(long id){
        return repositorioMeGusta.findById(id);
    }
    public MeGusta save(MeGusta meGusta){
        repositorioMeGusta.save(meGusta);
        //Actualizo el numero de MeGusta del Tweet
        return meGusta;
    }
    public MeGusta findByRecadoAndAutor(Recado recado, Autor autor){
        return repositorioMeGusta.findByRecadoAndAutor(recado, autor);
    }
    public boolean existsByRecadoAndAutor(Recado recado, Autor autor){
        return repositorioMeGusta.existsByRecadoAndAutor(recado, autor);
    }

    public boolean activoRecadoAndAutor(Recado recado, Autor autor){
        MeGusta meGusta=this.findByRecadoAndAutor(recado, autor);
        return meGusta!=null && meGusta.isEstado();
    }
    public long countByRecado(Recado recado){
        return repositorioMeGusta.countByRecado(recado);
    }

}
