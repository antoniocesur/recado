package com.example.demo.repositorios;

import com.example.demo.modelo.Autor;
import com.example.demo.modelo.UserDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RepositorioAutor extends CrudRepository<Autor, Long> {

    @Override
    public List<Autor> findAll();

    public Autor findFirstByEmail(String email);

    List<Autor> findByNombreContains(String nombre);
    Autor findByNombre(String nombre);
    Autor findByCuenta(String cuenta);
    Autor findByEmail(String email);

}
