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
@Table(name = "tipoDocIdentidad")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class TipoDocIdentidad {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String nombreTipoDocIdentidad;

    @OneToMany(mappedBy = "tipoDocIdentidad")
    private Set<Empleado> empleados = new HashSet<>();

    public TipoDocIdentidad(String nombreTipoDocIdentidad) {
        this.nombreTipoDocIdentidad = nombreTipoDocIdentidad;
    }
}
