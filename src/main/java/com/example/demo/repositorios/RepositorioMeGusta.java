package com.example.demo.repositorios;

import com.example.demo.modelo.Autor;
import com.example.demo.modelo.MeGusta;
import com.example.demo.modelo.Recado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioMeGusta extends JpaRepository<MeGusta, Long> {
    public List<MeGusta> findByAutor(Autor autor);
    public List<MeGusta> findByRecado(Recado recado);
    public MeGusta findById(long id);
    public MeGusta findByRecadoAndAutor(Recado recado, Autor autor);
    //@Query("select count(id) from MeGusta m where m.id = ?1") //También se puede hacer así
    public long countByRecado(Recado recado);
}
