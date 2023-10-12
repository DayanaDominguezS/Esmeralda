package com.HA.Esmeralda.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "nivelEscolaridad")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class NivelEscolaridad {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column
    private String nombreNivelEscolaridad;

    @OneToMany(mappedBy = "nivelEscolaridad")
    private Set<Empleado> empleados = new HashSet<>();

    public NivelEscolaridad(String nombreNivelEscolaridad) {
        this.nombreNivelEscolaridad = nombreNivelEscolaridad;
    }

}
