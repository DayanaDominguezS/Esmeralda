package com.HA.Esmeralda.servicio;

import com.HA.Esmeralda.domain.Departamento;
import com.HA.Esmeralda.domain.Empleado;
import com.HA.Esmeralda.domain.NivelEscolaridad;
import com.HA.Esmeralda.domain.Sexo;
import com.HA.Esmeralda.dto.DepartamentoDto;
import com.HA.Esmeralda.dto.EmpleadoDto;
import com.HA.Esmeralda.dto.NivelEscolaridadDto;
import com.HA.Esmeralda.dto.SexoDto;
import com.HA.Esmeralda.repositorio.DepartamentoRepository;
import com.HA.Esmeralda.repositorio.SexoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    public List<SexoDto> listarTodos(){

        List<SexoDto> sexoDtoList = new ArrayList<>();
        List<Sexo> sexoList = sexoRepository.findAll();

        for (Sexo sexo:sexoList){
            SexoDto sexoDto = mapper.convertValue(sexo, SexoDto.class);
            sexoDtoList.add(sexoDto);
        }
        return sexoDtoList;
    }

    public Optional<String> eliminarSexo (String nombreSexo) {
        Optional<String> mensajeEliminarSexo = null;
        sexoRepository.deleteByNombreSexo(nombreSexo);
        mensajeEliminarSexo = Optional.of(
                "El sexo: " + nombreSexo + " Se ha eliminado exitosamente");

        return mensajeEliminarSexo;

    }

}
