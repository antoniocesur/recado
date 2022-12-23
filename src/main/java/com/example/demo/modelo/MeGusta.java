package com.example.demo.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class MeGusta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    private Autor autor;

    @ManyToOne
    private Recado recado;

    private boolean estado; //Puede gustarle y después quitarle el meGusta

    public MeGusta(){ //Inicialmente me gusta
        estado=true;
    }

}
