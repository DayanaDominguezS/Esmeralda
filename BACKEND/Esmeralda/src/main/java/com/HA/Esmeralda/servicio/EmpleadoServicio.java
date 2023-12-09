package com.HA.Esmeralda.servicio;

import com.HA.Esmeralda.domain.Empleado;
import com.HA.Esmeralda.domain.NivelEscolaridad;
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


    // Agregar validacion de usuario ya existente
    public Optional<String> crearEmpleado (EmpleadoDto empleadoDto) {
        Optional<String> mensajeCrearEmpleado = null;

        Empleado empleado = mapper.convertValue(empleadoDto, Empleado.class);

        empleado.setDepartamento(departamentoRepository.getByNombreDepartamento(empleadoDto.getNombreDepartamento()).get());
        empleado.setNivelEscolaridad(nivelEscolaridadRepository.getByNombreNivelEscolaridad(empleadoDto.getNombreNivelEscolaridad()).get());
        empleado.setSexo(sexoRepository.getByNombreSexo(empleadoDto.getNombreSexo()).get());
        empleado.setTipoDocIdentidad(tipoDocIdentidadRepository.getByNombreTipoDocIdentidad(empleadoDto.getNombreTipoDocIdentidad()).get());

        empleadoRepository.save(empleado);

        mensajeCrearEmpleado = Optional.of(
                "El empleado con " + empleadoDto.getNombreTipoDocIdentidad() + " " + empleadoDto.getNumeroDocIdentidad()
                + " Se ha creado exitosamente");

        return mensajeCrearEmpleado;

    }

    public List<EmpleadoDto> listarTodos(){

        List<EmpleadoDto> empleadoDtoList = new ArrayList<>();
        List<Empleado> empleadoList = empleadoRepository.findAll();

        for (Empleado empleado:empleadoList){
            EmpleadoDto empleadoDto = mapper.convertValue(empleado, EmpleadoDto.class);

            empleadoDto.setNombreDepartamento((empleado.getDepartamento().getNombreDepartamento())); ;
            empleadoDto.setNombreNivelEscolaridad((empleado.getNivelEscolaridad().getNombreNivelEscolaridad()));
            empleadoDto.setNombreSexo((empleado.getSexo().getNombreSexo()));
            empleadoDto.setNombreTipoDocIdentidad(empleado.getTipoDocIdentidad().getNombreTipoDocIdentidad());

            empleadoDtoList.add(empleadoDto);
        }
        return empleadoDtoList;
    }

    // Agregar validacion de usuario ya existente
    public EmpleadoDto listarEmpleadoDocIdentidad(String numeroDocIdentidad){
        EmpleadoDto empleadoDto = null;
        empleadoRepository.getByNumeroDocIdentidad(numeroDocIdentidad);
        return empleadoDto;
    }

    // Agregar validacion de usuario ya existente
    public Optional<String> eliminarEmpleado (String numeroDocIdentidad) {
        Optional<String> mensajeEliminarEmpleado = null;
        empleadoRepository.deleteByNumeroDocIdentidad(numeroDocIdentidad);
        Empleado empleado = empleadoRepository.getByNumeroDocIdentidad(numeroDocIdentidad).get();
        mensajeEliminarEmpleado = Optional.of(
                "El empleado: " + empleado.getPrimerNombre() + empleado.getPrimerApellido()
                        + " Se ha eliminado exitosamente");

        return mensajeEliminarEmpleado;

    }

    public Optional<String> actualizarContrasena(String numeroDocIdentidad, String nuevaContrasena){
        String mensaje = null;
        if (empleadoRepository.getByNumeroDocIdentidad(numeroDocIdentidad).isEmpty()){
            mensaje = "No existe un empleado con documetno de indentidad numero: " + numeroDocIdentidad;
        }
        empleadoRepository.actualizarContrasena(numeroDocIdentidad,nuevaContrasena);
        mensaje = "Se actualizo correctamente la contrasena del empleado identificado con documento numero: " + numeroDocIdentidad;
        return Optional.of(mensaje);
    }

    public Optional<String> actualizarNumeroCelular(String numeroDocIdentidad, String nuevoNumCelular){
        String mensaje = null;
        if (empleadoRepository.getByNumeroDocIdentidad(numeroDocIdentidad).isEmpty()){
            mensaje = "No existe un empleado con documetno de indentidad numero: " + numeroDocIdentidad;
        }
        empleadoRepository.actualizarNumeroCelular(numeroDocIdentidad,nuevoNumCelular);
        mensaje = "Se actualizo correctamente el numero de celular del empleado identificado con documento numero: " + numeroDocIdentidad;
        return Optional.of(mensaje);
    }

    public Optional<String> actualizarNivelEscolaridad(String numeroDocIdentidad, String nombreNivelEscolaridad){
        String mensaje = null;
        if (empleadoRepository.getByNumeroDocIdentidad(numeroDocIdentidad).isEmpty()){
            mensaje = "No existe un empleado con documetno de indentidad numero: " + numeroDocIdentidad;
        }
        NivelEscolaridad nuevoNivelEscolaridad = nivelEscolaridadRepository.getByNombreNivelEscolaridad(nombreNivelEscolaridad).get();
        empleadoRepository.actualizarNivelEscolaridad(numeroDocIdentidad,nuevoNivelEscolaridad);
        mensaje = "Se actualizo correctamente el nivel de escolaridad del empleado identificado con documento numero: " + numeroDocIdentidad;
        return Optional.of(mensaje);
    }

    public Optional<String> actualizaractivo(String numeroDocIdentidad, Boolean nuevoActivo){
        String mensaje = null;
        if (empleadoRepository.getByNumeroDocIdentidad(numeroDocIdentidad).isEmpty()){
            mensaje = "No existe un empleado con documetno de indentidad numero: " + numeroDocIdentidad;
        }
        empleadoRepository.actualizarActivo(numeroDocIdentidad,nuevoActivo);
        mensaje = "Se actualizo correctamente estado activo del empleado identificado con documento numero: " + numeroDocIdentidad;
        return Optional.of(mensaje);
    }

}
