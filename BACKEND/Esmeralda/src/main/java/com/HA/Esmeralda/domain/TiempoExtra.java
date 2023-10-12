package com.HA.Esmeralda.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tiempoExtra")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class TiempoExtra {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column
    private LocalDateTime horaInicio;
    @Column
    private LocalDateTime horaFin;
    @Column
    private Integer duracionHoras;
    @Column
    private Boolean aprobado;
    @Column
    private Boolean liquidado;
    @Column
    private LocalDate fechaLiquidacion;

    public TiempoExtra(Long id, LocalDateTime horaInicio, LocalDateTime horaFin) {
        this.id = id;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        /** Revisar Carlos */
        this.duracionHoras = (horaFin.getHour()+(horaFin.getMinute()/60)+(horaFin.getSecond()/3600)) -
                (horaInicio.getHour()+(horaInicio.getMinute()/60)+(horaInicio.getSecond()/3600));
        this.aprobado = null;
        this.liquidado = false;
        this.fechaLiquidacion = null;

    }

}
