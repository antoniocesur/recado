package com.example.demo.modelo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotEmpty;
import java.sql.Date;
import java.sql.Time;

@Entity
@Transactional
@Data

public class Recado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable=false, unique = false)
    @NotEmpty
    private String contenido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="autor_id", nullable=false)
    private Autor autor;

    @Column(nullable=false, unique=false)
    @NotEmpty
    private Time fecha;

    public Recado(){

    }
    public Recado(String contenido, Autor autor, Time fecha){
        this.contenido=contenido;
        this.autor=autor;
        this.fecha=fecha;
    }

}
