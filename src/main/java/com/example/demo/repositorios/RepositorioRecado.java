package com.example.demo.repositorios;

import com.example.demo.modelo.Autor;
import com.example.demo.modelo.Recado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RepositorioRecado extends CrudRepository<Recado, Long> {

    @Override
    public List<Recado> findAll();
    public Recado findById(long id);

}
