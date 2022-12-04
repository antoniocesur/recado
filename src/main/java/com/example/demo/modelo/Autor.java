package com.example.demo.modelo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Set;

@Entity
@Data
@Transactional
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable=false, unique = false)
    @NotEmpty
    private String nombre;

    @Column(nullable=false, unique = false)
    @NotEmpty
    private String email;

    @Column(nullable=false, unique = false)
    @NotEmpty
    private String password;

    @Column(nullable=false, unique = false)
    @NotEmpty
    private int activo;

    @Column(nullable = false, unique=false)
    private String avatar;

    @OneToMany(mappedBy="autor")
    private Set<Recado> recados;

    public Autor(){
        activo=1;
    }

    public Autor(String nombre, String email, String password, String avatar){
        this.nombre=nombre;
        this.email=email;
        this.password=password;
        this.avatar=avatar;
    }


}
