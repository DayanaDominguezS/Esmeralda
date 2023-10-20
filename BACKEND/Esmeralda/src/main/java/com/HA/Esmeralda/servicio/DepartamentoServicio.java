package com.HA.Esmeralda.servicio;

import com.HA.Esmeralda.domain.Departamento;
import com.HA.Esmeralda.domain.Empleado;
import com.HA.Esmeralda.dto.DepartamentoDto;
import com.HA.Esmeralda.dto.EmpleadoDto;
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

    public Optional<String> crearDepartamento (DepartamentoDto departamentoDto) {
        Optional<String> mensajeCrearDepartamento = null;

        Departamento departamento = mapper.convertValue(departamentoDto, Departamento.class);

        departamentoRepository.save(departamento);
        mensajeCrearDepartamento = Optional.of(
                "El departamento " + departamentoDto.getNombreDepartamento()
                        + " Se ha creado exitosamente");

        return mensajeCrearDepartamento;

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

    public Optional<String> eliminarDepartamento (String nombreDepartamento) {
        Optional<String> mensajeEliminarDepartamento = null;
        departamentoRepository.deleteByNombreDepartamento(nombreDepartamento);
        mensajeEliminarDepartamento = Optional.of(
                "El departamento: " + nombreDepartamento + " Se ha eliminado exitosamente");

        return mensajeEliminarDepartamento;

    }

}
