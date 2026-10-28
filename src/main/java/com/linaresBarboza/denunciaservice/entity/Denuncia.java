package com.linaresBarboza.denunciaservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "denuncia")
public class Denuncia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 8, nullable = false)
    private String dni;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @Column(length = 30, nullable = false)
    private String tipo;

    @Column(length = 200, nullable = false)
    private String direccion;

    @Column(length = 255, nullable = false)
    private String descripcion;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 1")
    private Integer estado = 1;
}