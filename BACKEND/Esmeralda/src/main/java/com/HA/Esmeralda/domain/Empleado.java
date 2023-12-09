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
    private String numeroDocIdentidad;
    @Column
    private String lugarExpDocIdentidad;

    @Column (unique = true)
    private String correoElectronico;

    @Column
    private String contrasena;

    @Column
    private String numeroCelular;

    @ManyToOne
    @JoinColumn(name = "empleado_departamento_id")
    private Departamento departamento;

    @ManyToOne
    @JoinColumn(name = "empleado_nivelEscolaridad_id")
    private NivelEscolaridad nivelEscolaridad;

    @ManyToOne
    @JoinColumn(name = "empleado_sexo_id")
    private Sexo sexo;

    @ManyToOne
    @JoinColumn(name = "empleado_tipoDocIdentidad_id")
    private TipoDocIdentidad tipoDocIdentidad;

    @Column
    private Boolean activo = true;

    public Empleado(String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, LocalDate fechaNacimiento, String numeroDocIdentidad, String lugarExpDocIdentidad, String correoElectronico, String contrasena, String numeroCelular, Departamento departamento, NivelEscolaridad nivelEscolaridad, Sexo sexo, TipoDocIdentidad tipoDocIdentidad) {
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
        this.departamento = departamento;
        this.nivelEscolaridad = nivelEscolaridad;
        this.sexo = sexo;
        this.tipoDocIdentidad = tipoDocIdentidad;
    }

}



