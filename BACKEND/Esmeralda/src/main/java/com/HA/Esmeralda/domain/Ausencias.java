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

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ausencias")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Ausencias {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column
    private LocalDateTime horaInicioAusencia;
    @Column
    private LocalDateTime horaFinAusencia;
    @Column
    private Integer duracionHoras;
    @Column
    private String motivo;
    @Column
    private Boolean liquidado;
    @Column
    private LocalDate fechaLiquidacion;

    public Ausencias(Long id, LocalDateTime horaInicioAusencia, LocalDateTime horaFinAusencia, String motivo) {
        this.id = id;
        this.horaInicioAusencia = horaInicioAusencia;
        this.horaFinAusencia = horaFinAusencia;
        this.motivo = motivo;
        /** Falta asignar la formula que calcule la duración en horas
         * this.duracionHoras = ;
         * */
        this.liquidado = false;
        this.fechaLiquidacion = null;

    }


}
