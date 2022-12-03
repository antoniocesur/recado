package com.example.demo.modelo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotEmpty;
import java.sql.Date;

@Entity
@Transactional
@Data
public class Recado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable=false, unique = false)
    @NotEmpty
    private String contenido;

    @Column(nullable=false, unique = false)
    @NotEmpty
    @ManyToOne
    private Autor autor;

    @Column(nullable=false, unique=false)
    @NotEmpty
    private Date fecha;

}
