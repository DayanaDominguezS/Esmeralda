package com.HA.Esmeralda.servicio;

import com.HA.Esmeralda.domain.Empleado;
import com.HA.Esmeralda.dto.EmpleadoDto;
import com.HA.Esmeralda.repositorio.DepartamentoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class NivelEscolaridadServicio {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    public Optional<String> crearEmpleado (EmpleadoDto empleadoDto) {
        Optional<String> mensajeCrearEmpleado = null;

        Empleado empleado = mapper.convertValue(empleadoDto, Empleado.class);
        Empleado empleadoConsulta = empleadoRepository.getByNumeroDocIdentidad(empleadoDto.getNumeroDocIdentidad()).get();

        empleado.setDepartamento(empleadoConsulta.getDepartamento());
        empleado.setNivelEscolaridad(empleadoConsulta.getNivelEscolaridad());
        empleado.setSexo(empleadoConsulta.getSexo());
        empleado.setTipoDocIdentidad(empleadoConsulta.getTipoDocIdentidad());

        empleadoRepository.save(empleado);

        mensajeCrearEmpleado = Optional.of(
                "El empleado con" + empleadoDto.getTipoDocIdentidad() + empleadoDto.getNumeroDocIdentidad()
                        + " Se ha creado exitosamente");

        return mensajeCrearEmpleado;

    }
}
