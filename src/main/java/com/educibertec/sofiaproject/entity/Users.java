package com.educibertec.sofiaproject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "tb_usuarios")
public class Users implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idusuario;
    private String nombre;
    private String username;
    private String password;
    private int estado;
    @Transient
    private String token;
    @ManyToOne
    @JoinColumn(name = "rolId")
    private Rol rol;
}
