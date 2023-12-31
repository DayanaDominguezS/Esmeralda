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
@Table(name = "departamento")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column
    private String nombreDepartamento;

    @Column
    private String descripcionDepartamento;

    @OneToMany(mappedBy = "departamento")
    private Set<Empleado> empleados = new HashSet<>();

    public Departamento(String nombreDepartamento, String descripcionDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
        this.descripcionDepartamento = descripcionDepartamento;
    }

}
