package com.example.demo.modelo;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;

@Entity
@Data
@Transactional
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable=false, unique = true)
    @NotEmpty
    private String nombre;

    @Column(nullable=false, unique = true)
    @NotEmpty
    private String email;

    @Column(nullable=false, unique = false)
    @NotEmpty
    private String password;

    @Column(nullable=false, unique = false)
    @NotEmpty
    private int activo;

    @OneToMany
    ArrayList<Recado> recados;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Autor(){
        activo=1;
    }


}
