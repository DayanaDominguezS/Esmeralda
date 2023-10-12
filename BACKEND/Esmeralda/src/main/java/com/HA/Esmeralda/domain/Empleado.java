package com.HA.Esmeralda.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "empleado")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column
    private String primerNombre;
    @Column
    private String segundoNombre;
    @Column
    private String primerApellido;
    @Column
    private String segundoApellido;
    @Column
    private LocalDate fechaNacimiento;
    @Column (unique = true)
    private Long numeroDocIdentidad;
    @Column
    private String lugarExpDocIdentidad;

    @Column (unique = true)
    private String correoElectronico;

    @Column
    private String contrasena;

    @Column
    private String numeroCelular;

    public Empleado(String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, LocalDate fechaNacimiento, Long numeroDocIdentidad, String lugarExpDocIdentidad, String correoElectronico, String contrasena, String numeroCelular) {
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.fechaNacimiento = fechaNacimiento;
        this.numeroDocIdentidad = numeroDocIdentidad;
        this.lugarExpDocIdentidad = lugarExpDocIdentidad;
        this.correoElectronico = correoElectronico;
        this.contrasena = contrasena;
        this.numeroCelular = numeroCelular;
    }

}



