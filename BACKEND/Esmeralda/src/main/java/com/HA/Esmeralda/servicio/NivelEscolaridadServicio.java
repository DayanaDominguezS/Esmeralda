package com.HA.Esmeralda.servicio;

import com.HA.Esmeralda.domain.Departamento;
import com.HA.Esmeralda.domain.Empleado;
import com.HA.Esmeralda.domain.NivelEscolaridad;
import com.HA.Esmeralda.dto.DepartamentoDto;
import com.HA.Esmeralda.dto.EmpleadoDto;
import com.HA.Esmeralda.dto.NivelEscolaridadDto;
import com.HA.Esmeralda.repositorio.DepartamentoRepository;
import com.HA.Esmeralda.repositorio.NivelEscolaridadRepository;
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
    private NivelEscolaridadRepository nivelEscolaridadRepository;

    public Optional<String> crearNivelEscolaridad (NivelEscolaridadDto nivelEscolaridadDto) {
            Optional<String> mensajeCrearNivelEscolaridad = null;

        NivelEscolaridad nivelEscolaridad = mapper.convertValue(nivelEscolaridadDto, NivelEscolaridad.class);

        nivelEscolaridadRepository.save(nivelEscolaridad);
        mensajeCrearNivelEscolaridad = Optional.of(
                    "El nivel de escolaridad " + nivelEscolaridadDto.getNombreNivelEscolaridad()
                            + " Se ha creado exitosamente");

            return mensajeCrearNivelEscolaridad;

        }

    }
