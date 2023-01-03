package com.example.demo.modelo;

import com.example.demo.servicios.ServicioMeGusta;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Formula;
import org.springframework.beans.factory.annotation.Autowired;
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
    private Date fecha;

    private String imagen;

    //Cuento el número de "me gusta" activos para este recado
    @Formula(value = "(SELECT COUNT(*) FROM me_gusta m WHERE m.recado_id=id AND m.estado=true)")
    private long numMeGusta;

    //Indica si le gusta el recado al usuario activo para cambiar el color del corazón
    @Transient
    private boolean meGustaActual;

    public Recado(){
        meGustaActual=false;
    }
    public Recado(String contenido, Autor autor, Date fecha){
        this.contenido=contenido;
        this.autor=autor;
        this.fecha=fecha;
    }

    /*@Transient
    public long getNumMeGusta() {
        return numMeGusta;
    }*/

}
