package com.HA.Esmeralda.servicio;

import com.HA.Esmeralda.domain.Departamento;
import com.HA.Esmeralda.dto.DepartamentoDto;
import com.HA.Esmeralda.exceptions.DuplicadoException;
import com.HA.Esmeralda.exceptions.RecursoNoEncontradoException;
import com.HA.Esmeralda.repositorio.DepartamentoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class DepartamentoServicio {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    public Optional<String> crearDepartamento (DepartamentoDto departamentoDto) throws DuplicadoException {
        String mensajeCrearDepartamento = null;

        if (departamentoRepository.getByNombreDepartamento(departamentoDto.getNombreDepartamento()).isPresent()){
            mensajeCrearDepartamento = "Ya existe un departamento creado con el nombre: " + departamentoDto.getNombreDepartamento();
            log.error(mensajeCrearDepartamento);
            throw new DuplicadoException(mensajeCrearDepartamento);
        }

        Departamento departamento = mapper.convertValue(departamentoDto, Departamento.class);
        departamentoRepository.save(departamento);
        mensajeCrearDepartamento = "El departamento " + departamentoDto.getNombreDepartamento() + " se ha creado exitosamente!";

        log.info(mensajeCrearDepartamento);
        return Optional.ofNullable(mensajeCrearDepartamento);

    }

    public List<DepartamentoDto> listarTodos(){
        List<DepartamentoDto> departamentoDtoList = new ArrayList<>();
        List<Departamento> departamentoList = departamentoRepository.findAll();

        for (Departamento departamento:departamentoList){
            DepartamentoDto departamentoDto = mapper.convertValue(departamento, DepartamentoDto.class);
            departamentoDtoList.add(departamentoDto);
        }

        return departamentoDtoList;
    }

    public Optional<DepartamentoDto> obtenerPorNombre(String nombreDepartamento) throws RecursoNoEncontradoException {
        DepartamentoDto departamentoDto = null;
        Optional<Departamento> optionalDepartamento = departamentoRepository.getByNombreDepartamento(nombreDepartamento);

        if (optionalDepartamento.isEmpty()){
            String mensaje = "No existe un departamento con nombre " + nombreDepartamento;
            log.error(mensaje);
            throw new RecursoNoEncontradoException(mensaje);
        }

        departamentoDto = mapper.convertValue(optionalDepartamento.get(),DepartamentoDto.class);

        log.info("Se obtuvo exitosamente el departamento con nombre: " + nombreDepartamento);
        return Optional.ofNullable(departamentoDto);
    }


    public Optional<String> eliminarDepartamento (String nombreDepartamento) throws RecursoNoEncontradoException {
        String mensajeEliminarDepartamento = null;

        if (this.obtenerPorNombre(nombreDepartamento).isPresent()){
            departamentoRepository.deleteByNombreDepartamento(nombreDepartamento);
        }

        mensajeEliminarDepartamento = "Se ha eliminado exitosamente el departamente con nombre: " + nombreDepartamento;
        log.info(mensajeEliminarDepartamento);
        return Optional.ofNullable(mensajeEliminarDepartamento);
    }

}
