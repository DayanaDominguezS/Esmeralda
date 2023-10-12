package com.HA.Esmeralda.dto;

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
public class EmpleadoDto {

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
    @Column
    private Long numeroDocIdentidad;
    @Column
    private String lugarExpDocIdentidad;

    public EmpleadoDto(String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, LocalDate fechaNacimiento, Long numeroDocIdentidad, String lugarExpDocIdentidad) {
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.fechaNacimiento = fechaNacimiento;
        this.numeroDocIdentidad = numeroDocIdentidad;
        this.lugarExpDocIdentidad = lugarExpDocIdentidad;
    }

}



