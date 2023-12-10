package com.HA.Esmeralda.servicio;

import com.HA.Esmeralda.domain.Empleado;
import com.HA.Esmeralda.domain.NivelEscolaridad;
import com.HA.Esmeralda.dto.EmpleadoDto;
import com.HA.Esmeralda.exceptions.DuplicadoException;
import com.HA.Esmeralda.exceptions.RecursoNoEncontradoException;
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


    public Optional<String> crearEmpleado (EmpleadoDto empleadoDto) throws DuplicadoException {
        String mensajeCrearEmpleado = null;

        if (empleadoRepository.getByNumeroDocIdentidad(empleadoDto.getNumeroDocIdentidad()).isPresent()){
            mensajeCrearEmpleado = "Ya existe un empleado con documento de identidad numero: " + empleadoDto.getNumeroDocIdentidad();
            log.error(mensajeCrearEmpleado);
            throw new DuplicadoException(mensajeCrearEmpleado);
        }

        Empleado empleado = mapper.convertValue(empleadoDto, Empleado.class);

        empleado.setDepartamento(departamentoRepository.getByNombreDepartamento(empleadoDto.getNombreDepartamento()).get());
        empleado.setNivelEscolaridad(nivelEscolaridadRepository.getByNombreNivelEscolaridad(empleadoDto.getNombreNivelEscolaridad()).get());
        empleado.setSexo(sexoRepository.getByNombreSexo(empleadoDto.getNombreSexo()).get());
        empleado.setTipoDocIdentidad(tipoDocIdentidadRepository.getByNombreTipoDocIdentidad(empleadoDto.getNombreTipoDocIdentidad()).get());

        empleadoRepository.save(empleado);

        mensajeCrearEmpleado = "El empleado con " + empleadoDto.getNombreTipoDocIdentidad() + " " + empleadoDto.getNumeroDocIdentidad()
                + " Se ha creado exitosamente";

        log.info(mensajeCrearEmpleado);
        return Optional.ofNullable(mensajeCrearEmpleado);
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

    public Optional<EmpleadoDto> obtenerEmpleadoDocIdentidad(String numeroDocIdentidad) throws RecursoNoEncontradoException {
        EmpleadoDto empleadoDto = null;

        Optional<Empleado> optionalEmpleado = empleadoRepository.getByNumeroDocIdentidad(numeroDocIdentidad);

        if (optionalEmpleado.isEmpty()){
            String mensaje = "No existe un empleado con documento de identidad numero: " + numeroDocIdentidad;
            log.error(mensaje);
            throw new RecursoNoEncontradoException(mensaje);
        }

        empleadoDto = mapper.convertValue(optionalEmpleado.get(),EmpleadoDto.class);

        empleadoDto.setNombreDepartamento(optionalEmpleado.get().getDepartamento().getNombreDepartamento()); ;
        empleadoDto.setNombreNivelEscolaridad(optionalEmpleado.get().getNivelEscolaridad().getNombreNivelEscolaridad());
        empleadoDto.setNombreSexo(optionalEmpleado.get().getSexo().getNombreSexo());
        empleadoDto.setNombreTipoDocIdentidad(optionalEmpleado.get().getTipoDocIdentidad().getNombreTipoDocIdentidad());

        log.info("Se obtuvo exitosamente al empleado con documento de identidad numero: " + numeroDocIdentidad);
        return Optional.ofNullable(empleadoDto);
    }

    public Optional<String> eliminarEmpleado (String numeroDocIdentidad) throws RecursoNoEncontradoException {
        String mensajeEliminarEmpleado = null;
        Optional<EmpleadoDto> optionalEmpleadoDto = this.obtenerEmpleadoDocIdentidad(numeroDocIdentidad);
        empleadoRepository.deleteByNumeroDocIdentidad(numeroDocIdentidad);
        mensajeEliminarEmpleado = "Se elimino correctamente al empleado con documento de identidad numero: " + numeroDocIdentidad;
        log.info(mensajeEliminarEmpleado);
        return Optional.ofNullable(mensajeEliminarEmpleado);
    }

    public Optional<String> actualizarContrasena(String numeroDocIdentidad, String nuevaContrasena) throws RecursoNoEncontradoException {
        String mensaje = null;
        Optional<EmpleadoDto> optionalEmpleadoDto = this.obtenerEmpleadoDocIdentidad(numeroDocIdentidad);
        empleadoRepository.actualizarContrasena(numeroDocIdentidad,nuevaContrasena);
        mensaje = "Se actualizo correctamente la contrasena del empleado identificado con documento numero: " + numeroDocIdentidad;
        log.info(mensaje);
        return Optional.of(mensaje);
    }

    public Optional<String> actualizarNumeroCelular(String numeroDocIdentidad, String nuevoNumCelular) throws RecursoNoEncontradoException {
        String mensaje = null;
        Optional<EmpleadoDto> optionalEmpleadoDto = this.obtenerEmpleadoDocIdentidad(numeroDocIdentidad);
        empleadoRepository.actualizarNumeroCelular(numeroDocIdentidad,nuevoNumCelular);
        mensaje = "Se actualizo correctamente el numero de celular del empleado identificado con documento numero: " + numeroDocIdentidad;
        log.info(mensaje);
        return Optional.of(mensaje);
    }

    public Optional<String> actualizarNivelEscolaridad(String numeroDocIdentidad, String nombreNivelEscolaridad) throws RecursoNoEncontradoException {
        String mensaje = null;
        Optional<EmpleadoDto> optionalEmpleadoDto = this.obtenerEmpleadoDocIdentidad(numeroDocIdentidad);
        NivelEscolaridad nuevoNivelEscolaridad = nivelEscolaridadRepository.getByNombreNivelEscolaridad(nombreNivelEscolaridad).get();
        empleadoRepository.actualizarNivelEscolaridad(numeroDocIdentidad,nuevoNivelEscolaridad);
        mensaje = "Se actualizo correctamente el nivel de escolaridad del empleado identificado con documento numero: " + numeroDocIdentidad;
        log.info(mensaje);
        return Optional.of(mensaje);
    }

    public Optional<String> actualizaractivo(String numeroDocIdentidad, Boolean nuevoActivo) throws RecursoNoEncontradoException {
        String mensaje = null;
        Optional<EmpleadoDto> optionalEmpleadoDto = this.obtenerEmpleadoDocIdentidad(numeroDocIdentidad);
        empleadoRepository.actualizarActivo(numeroDocIdentidad,nuevoActivo);
        mensaje = "Se actualizo correctamente estado activo del empleado identificado con documento numero: " + numeroDocIdentidad;
        log.info(mensaje);
        return Optional.of(mensaje);
    }

}
