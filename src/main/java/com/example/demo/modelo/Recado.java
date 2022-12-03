package com.example.demo.modelo;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotEmpty;
import java.sql.Date;

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

    @JoinColumn(nullable=false, unique = false)
    @NotEmpty
    @ManyToOne
    private Autor autor;

    @Column(nullable=false, unique=false)
    @NotEmpty
    private Date fecha;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
