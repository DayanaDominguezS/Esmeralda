package com.HA.Esmeralda.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "permiso")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class PermisoDto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private LocalDateTime fechaHoraInicio;
    @Column
    private LocalDateTime fechaHoraFin;
    @Column
    private String notas;
    @Column
    private Boolean aprobado;

    /** Revisar Carlos*/
    public PermisoDto(LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin, String notas) {
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
        this.notas = notas;
        this.aprobado = null;
    }

}
