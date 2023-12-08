package com.HA.Esmeralda.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    private String nombreDepartamento;
    private String nombreNivelEscolaridad;
    private String nombreSexo;
    private String nombreTipoDocIdentidad;

}



