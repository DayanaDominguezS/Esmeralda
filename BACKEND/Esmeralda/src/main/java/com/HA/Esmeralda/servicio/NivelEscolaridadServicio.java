package com.HA.Esmeralda.servicio;

import com.HA.Esmeralda.domain.NivelEscolaridad;
import com.HA.Esmeralda.dto.NivelEscolaridadDto;
import com.HA.Esmeralda.exceptions.DuplicadoException;
import com.HA.Esmeralda.exceptions.RecursoNoEncontradoException;
import com.HA.Esmeralda.repositorio.NivelEscolaridadRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class NivelEscolaridadServicio {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private NivelEscolaridadRepository nivelEscolaridadRepository;

    public Optional<String> crearNivelEscolaridad (NivelEscolaridadDto nivelEscolaridadDto) throws DuplicadoException {
        String mensajeCrearNivelEscolaridad = null;

        if (nivelEscolaridadRepository.getByNombreNivelEscolaridad(nivelEscolaridadDto.getNombreNivelEscolaridad()).isPresent()){
            mensajeCrearNivelEscolaridad = "Ya existe un nivel de escolaridad con nombre: " + nivelEscolaridadDto.getNombreNivelEscolaridad();
            log.error(mensajeCrearNivelEscolaridad);
            throw new DuplicadoException(mensajeCrearNivelEscolaridad);
        }

        NivelEscolaridad nivelEscolaridad = mapper.convertValue(nivelEscolaridadDto, NivelEscolaridad.class);
        nivelEscolaridadRepository.save(nivelEscolaridad);
        mensajeCrearNivelEscolaridad = "El nivel de escolaridad " + nivelEscolaridadDto.getNombreNivelEscolaridad()
                            + " Se ha creado exitosamente";

        log.info(mensajeCrearNivelEscolaridad);
        return Optional.ofNullable(mensajeCrearNivelEscolaridad);
    }

    public List<NivelEscolaridadDto> listarTodos(){
        List<NivelEscolaridadDto> nivelEscolaridadDtoList = new ArrayList<>();
        List<NivelEscolaridad> nivelEscolaridadList = nivelEscolaridadRepository.findAll();

        for (NivelEscolaridad nivelEscolaridad:nivelEscolaridadList){
            NivelEscolaridadDto nivelEscolaridadDto = mapper.convertValue(nivelEscolaridad, NivelEscolaridadDto.class);
            nivelEscolaridadDtoList.add(nivelEscolaridadDto);
        }
        return nivelEscolaridadDtoList;
    }

    public Optional<NivelEscolaridadDto> obtenerPorNombre(String nombreNivel) throws RecursoNoEncontradoException {
        NivelEscolaridadDto nivelEscolaridadDto = null;
        Optional<NivelEscolaridad> optionalNivelEscolaridad = nivelEscolaridadRepository.getByNombreNivelEscolaridad(nombreNivel);

        if (optionalNivelEscolaridad.isEmpty()){
            String mensaje = "No existe un nivel de escolaridad con nombre " + nombreNivel;
            log.error(mensaje);
            throw new RecursoNoEncontradoException(mensaje);
        }

        nivelEscolaridadDto = mapper.convertValue(optionalNivelEscolaridad.get(),NivelEscolaridadDto.class);

        log.info("Se obtuvo exitosamente el nivel de escolaridad con nombre: " + nombreNivel);
        return Optional.ofNullable(nivelEscolaridadDto);
    }

    public Optional<String> eliminarNivelEscolaridad (String nombreNivelEscolar) throws RecursoNoEncontradoException {
        String mensajeEliminarNivelEscolaridad = null;

        if (this.obtenerPorNombre(nombreNivelEscolar).isPresent()){
            nivelEscolaridadRepository.deleteByNombreNivelEscolaridad(nombreNivelEscolar);
        }

        mensajeEliminarNivelEscolaridad = "El nivel de escolaridad: " + nombreNivelEscolar + " se ha eliminado exitosamente";
        log.info(mensajeEliminarNivelEscolaridad);
        return Optional.ofNullable(mensajeEliminarNivelEscolaridad);
    }

}
