package com.example.demo.modelo;

import com.example.demo.validation.ValidEmail;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Transactional
public class Autor {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable=false, unique = false)
    @NotEmpty
    private String nombre;

    @Column(nullable = false, unique = false)
    @NotEmpty
    private String cuenta;

    @Column(nullable=false, unique = false)
    @NotEmpty
    @ValidEmail
    private String email;

    @Column(nullable=false, unique = false)
    @NotEmpty
    private String password;

    @Column(nullable=false, unique = false)
    @NotEmpty
    private int activo;

    @Column(nullable = false, unique=false)
    private String avatar;

    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinTable(
            name="users_roles",
            joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="ID")},
            inverseJoinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="ID")})
    private List<Role> roles = new ArrayList<>();

    @OneToMany(mappedBy="autor")
    private Set<Recado> recados;

    public Autor(){
        activo=1;
    }

    public Autor(String nombre, String email, String password, String avatar){
        this.nombre=nombre;
        this.cuenta=nombre.replace(" ", ""); //Quito los espacios y ese es el nombre de usuario (de momento)
        this.email=email;
        this.password=password;
        this.avatar=avatar;
    }

}
