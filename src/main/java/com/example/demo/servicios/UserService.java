package com.example.demo.servicios;

import com.example.demo.modelo.Autor;
import com.example.demo.modelo.UserDto;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    Autor findByEmail(String email);

    List<UserDto> findAllUsers();
    Autor findByNombre(String nombre);
}
