package com.HA.Esmeralda.servicio;

import com.HA.Esmeralda.domain.Sexo;
import com.HA.Esmeralda.dto.SexoDto;
import com.HA.Esmeralda.exceptions.DuplicadoException;
import com.HA.Esmeralda.exceptions.RecursoNoEncontradoException;
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

    public Optional<String> crearSexo(SexoDto sexoDto) throws DuplicadoException {
        String mensajeCrearSexo = null;

        if (sexoRepository.getByNombreSexo(sexoDto.getNombreSexo()).isPresent()){
            mensajeCrearSexo = "Ya existe un sexo con nombre: " + sexoDto.getNombreSexo();
            log.error(mensajeCrearSexo);
            throw new DuplicadoException(mensajeCrearSexo);
        }

        Sexo sexo = mapper.convertValue(sexoDto, Sexo.class);
        sexoRepository.save(sexo);
        mensajeCrearSexo = "El sexo " + sexoDto.getNombreSexo() + " se ha creado exitosamente";

        log.info(mensajeCrearSexo);
        return Optional.ofNullable(mensajeCrearSexo);
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

    public Optional<SexoDto> obtenerPorNombre(String nombreSexo) throws RecursoNoEncontradoException {
        SexoDto sexoDto = null;
        Optional<Sexo> optionalSexo = sexoRepository.getByNombreSexo(nombreSexo);

        if (optionalSexo.isEmpty()){
            String mensaje = "No existe un sexo con nombre: " + nombreSexo;
            log.error(mensaje);
            throw new RecursoNoEncontradoException(mensaje);
        }

        sexoDto = mapper.convertValue(optionalSexo.get(), SexoDto.class);

        log.info("Se obtuvo exitosamente el sexo con nombre: " + nombreSexo);
        return Optional.ofNullable(sexoDto);
    }

    public Optional<String> eliminarSexo (String nombreSexo) throws RecursoNoEncontradoException {
        String mensajeEliminarSexo = null;

        if (this.obtenerPorNombre(nombreSexo).isPresent()){
            sexoRepository.deleteByNombreSexo(nombreSexo);
        }

        mensajeEliminarSexo = "El sexo: " + nombreSexo + " se ha eliminado exitosamente";
        log.info(mensajeEliminarSexo);
        return Optional.ofNullable(mensajeEliminarSexo);
    }

}
