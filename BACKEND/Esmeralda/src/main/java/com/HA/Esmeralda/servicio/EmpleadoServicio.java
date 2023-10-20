package com.HA.Esmeralda.servicio;

import com.HA.Esmeralda.domain.Empleado;
import com.HA.Esmeralda.dto.EmpleadoDto;
import com.HA.Esmeralda.repositorio.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class EmpleadoServicio {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private EmpleadoRepository empleadoRepository;
    @Autowired
    private DepartamentoRepository departamentoRepository;
    @Autowired
    private NivelEscolaridadRepository nivelEscolaridadRepository;
    @Autowired
    private SexoRepository sexoRepository;
    @Autowired
    private TipoDocIdentidadRepository tipoDocIdentidadRepository;

    public Optional<String> crearEmpleado (EmpleadoDto empleadoDto) {
        Optional<String> mensajeCrearEmpleado = null;

        Empleado empleado = mapper.convertValue(empleadoDto, Empleado.class);

        empleado.setDepartamento(departamentoRepository.getByNombreDepartamento(empleadoDto.getDepartamento()).get());
        empleado.setNivelEscolaridad(nivelEscolaridadRepository.getByNombreNivelEscolaridad(empleadoDto.getNivelEscolaridad()).get());
        empleado.setSexo(sexoRepository.getByNombreSexo(empleadoDto.getSexo()).get());
        empleado.setTipoDocIdentidad(tipoDocIdentidadRepository.getByNombreTipoDocIdentidad(empleadoDto.getTipoDocIdentidad()).get());

        empleadoRepository.save(empleado);

        mensajeCrearEmpleado = Optional.of(
                "El empleado con" + empleadoDto.getTipoDocIdentidad() + empleadoDto.getNumeroDocIdentidad()
                + " Se ha creado exitosamente");

        return mensajeCrearEmpleado;

    }

    public List<EmpleadoDto> listarTodos(){

        List<EmpleadoDto> empleadoDtoList = new ArrayList<>();
        List<Empleado> empleadoList = empleadoRepository.findAll();

        for (Empleado empleado:empleadoList){
            EmpleadoDto empleadoDto = mapper.convertValue(empleado, EmpleadoDto.class);

            empleadoDto.setDepartamento(empleado.getDepartamento().getNombreDepartamento()); ;
            empleadoDto.setNivelEscolaridad(empleado.getNivelEscolaridad().getNombreNivelEscolaridad());
            empleadoDto.setSexo(empleado.getSexo().getNombreSexo());
            empleadoDto.setTipoDocIdentidad(empleado.getTipoDocIdentidad().getNombreTipoDocIdentidad());

            empleadoDtoList.add(empleadoDto);
        }
        return empleadoDtoList;
    }

    public EmpleadoDto listarEmpleadoDocIdentidad(String numeroDocIdentidad){
        EmpleadoDto empleadoDto = null;
        empleadoRepository.getByNumeroDocIdentidad(numeroDocIdentidad);
        return empleadoDto;
    }

    public Optional<String> eliminarEmpleado (String numeroDocIdentidad) {
        Optional<String> mensajeEliminarEmpleado = null;
        empleadoRepository.deleteByNumeroDocIdentidad(numeroDocIdentidad);
        Empleado empleado = empleadoRepository.getByNumeroDocIdentidad(numeroDocIdentidad).get();
        mensajeEliminarEmpleado = Optional.of(
                "El empleado: " + empleado.getPrimerNombre() + empleado.getPrimerApellido()
                        + " Se ha eliminado exitosamente");

        return mensajeEliminarEmpleado;

    }
    /** Revisar como se hace el metodo actualizar sin que borre todos los campos y toque eliminar
     * todo y crearlo todo nuevamente
     * public Optional<String> actualizarEmpleado (EmpleadoDto empleadoDto) {
        Optional<String> mensajeCrearEmpleado = null;

        return mensajeCrearEmpleado;

    }*/


}
