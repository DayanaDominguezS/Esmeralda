package com.HA.Esmeralda.servicio;

import com.HA.Esmeralda.domain.TipoDocIdentidad;
import com.HA.Esmeralda.dto.TipoDocIdentidadDto;
import com.HA.Esmeralda.exceptions.DuplicadoException;
import com.HA.Esmeralda.exceptions.RecursoNoEncontradoException;
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

    public Optional<String> crearTipoDocIdentidad(TipoDocIdentidadDto tipoDocIdentidadDto) throws DuplicadoException {
        String mensajeCrearTipoDocIdentidad = null;

        if (tipoDocIdentidadRepository.getByNombreTipoDocIdentidad(tipoDocIdentidadDto.getNombreTipoDocIdentidad()).isPresent()){
            mensajeCrearTipoDocIdentidad = "Ya existe un tipo de documento identidad con nombre: " + tipoDocIdentidadDto.getNombreTipoDocIdentidad();
            log.error(mensajeCrearTipoDocIdentidad);
            throw new DuplicadoException(mensajeCrearTipoDocIdentidad);
        }

        TipoDocIdentidad tipoDocIdentidad = mapper.convertValue(tipoDocIdentidadDto, TipoDocIdentidad.class);
        tipoDocIdentidadRepository.save(tipoDocIdentidad);
        mensajeCrearTipoDocIdentidad = "El tipo de documento de identidad " + tipoDocIdentidadDto.getNombreTipoDocIdentidad()
                        + " se ha creado exitosamente";

        log.info(mensajeCrearTipoDocIdentidad);
        return Optional.ofNullable(mensajeCrearTipoDocIdentidad);
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

    public Optional<TipoDocIdentidadDto> obtenerPorNombre(String nombreTipoDoc) throws RecursoNoEncontradoException {
        TipoDocIdentidadDto tipoDocIdentidadDto = null;
        Optional<TipoDocIdentidad> optionalTipoDocIdentidad = tipoDocIdentidadRepository.getByNombreTipoDocIdentidad(nombreTipoDoc);

        if (optionalTipoDocIdentidad.isEmpty()){
            String mensaje = "No existe un tipo de documento identidad con nombre: " + nombreTipoDoc;
            log.error(mensaje);
            throw new RecursoNoEncontradoException(mensaje);
        }

        tipoDocIdentidadDto = mapper.convertValue(optionalTipoDocIdentidad.get(),TipoDocIdentidadDto.class);

        log.info("Se obtuvo exitosamente el tipo de documento identidad con nombre: " + nombreTipoDoc);
        return Optional.ofNullable(tipoDocIdentidadDto);
    }

    public Optional<String> eliminarTipoDocIdentidad(String nombreDocIdentidad) throws RecursoNoEncontradoException {
        String mensajeEliminarTipoDocIdentidad = null;

        if (this.obtenerPorNombre(nombreDocIdentidad).isPresent()){
            tipoDocIdentidadRepository.deleteByNombreTipoDocIdentidad(nombreDocIdentidad); ;
        }

        mensajeEliminarTipoDocIdentidad = "El tipo de documento de identidad: " + nombreDocIdentidad + " se ha eliminado exitosamente";
        log.info(mensajeEliminarTipoDocIdentidad);
        return Optional.ofNullable(mensajeEliminarTipoDocIdentidad);
    }

}
