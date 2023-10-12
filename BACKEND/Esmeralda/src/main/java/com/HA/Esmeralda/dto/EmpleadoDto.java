package com.HA.Esmeralda.dto;

import com.HA.Esmeralda.domain.Departamento;
import com.HA.Esmeralda.domain.NivelEscolaridad;
import com.HA.Esmeralda.domain.Sexo;
import com.HA.Esmeralda.domain.TipoDocIdentidad;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmpleadoDto {

    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;
    private String numeroDocIdentidad;
    private String lugarExpDocIdentidad;
    private String correoElectronico;
    private String contrasena;
    private String numeroCelular;
    private String departamento;
    private String nivelEscolaridad;
    private String sexo;
    private String tipoDocIdentidad;

}



