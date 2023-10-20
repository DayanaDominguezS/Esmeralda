package com.HA.Esmeralda.servicio;

import com.HA.Esmeralda.domain.Empleado;
import com.HA.Esmeralda.domain.Sexo;
import com.HA.Esmeralda.domain.TipoDocIdentidad;
import com.HA.Esmeralda.dto.EmpleadoDto;
import com.HA.Esmeralda.dto.SexoDto;
import com.HA.Esmeralda.dto.TipoDocIdentidadDto;
import com.HA.Esmeralda.repositorio.DepartamentoRepository;
import com.HA.Esmeralda.repositorio.TipoDocIdentidadRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TipoDocIdentidadServicio {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private TipoDocIdentidadRepository tipoDocIdentidadRepository;

    public Optional<String> crearTipoDocIdentidad (TipoDocIdentidadDto tipoDocIdentidadDto) {
        Optional<String> mensajeCrearTipoDocIdentidad = null;

        TipoDocIdentidad tipoDocIdentidad = mapper.convertValue(tipoDocIdentidadDto, TipoDocIdentidad.class);

        tipoDocIdentidadRepository.save(tipoDocIdentidad);
        mensajeCrearTipoDocIdentidad = Optional.of(
                "El tipo de documento de identidad " + tipoDocIdentidadDto.getNombreTipoDocIdentidad()
                        + " Se ha creado exitosamente");

        return mensajeCrearTipoDocIdentidad;

    }

    public List<TipoDocIdentidadDto> listarTodos(){

        List<TipoDocIdentidadDto> tipoDocIdentidadDtoList = new ArrayList<>();
        List<TipoDocIdentidad> tipoDocIdentidadList = tipoDocIdentidadRepository.findAll();

        for (TipoDocIdentidad tipoDocIdentidad:tipoDocIdentidadList){
            TipoDocIdentidadDto tipoDocIdentidadDto = mapper.convertValue(tipoDocIdentidad, TipoDocIdentidadDto.class);
            tipoDocIdentidadDtoList.add(tipoDocIdentidadDto);
        }
        return tipoDocIdentidadDtoList;
    }

    public Optional<String> eliminarTipoDocIdentidad (String nombreDocIdentidad) {
        Optional<String> mensajeEliminarTipoDocIdentidad = null;
        tipoDocIdentidadRepository.deleteByNombreTipoDocIdentidad(nombreDocIdentidad); ;
        mensajeEliminarTipoDocIdentidad = Optional.of(
                "El tipo de documento de identidad: " + nombreDocIdentidad + " Se ha eliminado exitosamente");

        return mensajeEliminarTipoDocIdentidad;

    }

}
