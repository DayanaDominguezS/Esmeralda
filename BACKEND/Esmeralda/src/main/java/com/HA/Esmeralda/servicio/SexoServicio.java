package com.HA.Esmeralda.servicio;

import com.HA.Esmeralda.domain.Departamento;
import com.HA.Esmeralda.domain.Empleado;
import com.HA.Esmeralda.domain.Sexo;
import com.HA.Esmeralda.dto.DepartamentoDto;
import com.HA.Esmeralda.dto.EmpleadoDto;
import com.HA.Esmeralda.dto.SexoDto;
import com.HA.Esmeralda.repositorio.DepartamentoRepository;
import com.HA.Esmeralda.repositorio.SexoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class SexoServicio {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private SexoRepository sexoRepository;

    public Optional<String> crearSexo (SexoDto sexoDto) {
        Optional<String> mensajeCrearSexo = null;

        Sexo sexo = mapper.convertValue(sexoDto, Sexo.class);

        sexoRepository.save(sexo);
        mensajeCrearSexo = Optional.of(
                "El sexo " + sexoDto.getNombreSexo()
                        + " Se ha creado exitosamente");

        return mensajeCrearSexo;

    }

}
