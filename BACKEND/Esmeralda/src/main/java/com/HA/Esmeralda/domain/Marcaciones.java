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
@Table(name = "marcaciones")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Marcaciones {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column
    private LocalDateTime horaMarcacion1;

    @Column
    private LocalDateTime horaMarcacion2;

    @Column
    private LocalDateTime horaMarcacion3;

    @Column
    private LocalDateTime horaMarcacion4;

    public Marcaciones(LocalDateTime horaMarcacion1, LocalDateTime horaMarcacion2, LocalDateTime horaMarcacion3, LocalDateTime horaMarcacion4) {
        this.horaMarcacion1 = horaMarcacion1;
        this.horaMarcacion2 = horaMarcacion2;
        this.horaMarcacion3 = horaMarcacion3;
        this.horaMarcacion4 = horaMarcacion4;
    }
}




