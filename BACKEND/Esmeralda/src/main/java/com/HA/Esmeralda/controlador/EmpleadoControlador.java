package com.HA.Esmeralda.controlador;

import com.HA.Esmeralda.dto.EmpleadoDto;
import com.HA.Esmeralda.exceptions.DuplicadoException;
import com.HA.Esmeralda.exceptions.RecursoNoEncontradoException;
import com.HA.Esmeralda.servicio.EmpleadoServicio;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empleados")
@Tag(name = "Empleados")
@Slf4j
public class EmpleadoControlador {
    @Autowired
    private EmpleadoServicio empleadoServicio;

    @Autowired
    ObjectMapper mapper;

    @PostMapping("/guardar")
    @Operation(summary = "Guardar un empleado")
    public ResponseEntity<String> guardarEmpleado(@RequestBody EmpleadoDto empleadoDto) throws DuplicadoException {
        return empleadoServicio.crearEmpleado(empleadoDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.internalServerError().build());
    }

    @GetMapping("/listarTodos")
    @Operation(summary = "Obtener todos los empleados")
    public ResponseEntity<List<EmpleadoDto>> listarTodos() {
        return ResponseEntity.ok(empleadoServicio.listarTodos());
    }

    @GetMapping("/obtenerPorDocIdentidad/{docIdentidad}")
    @Operation(summary = "Listar empleado por numero de documento de identidad")
    public ResponseEntity<EmpleadoDto> obtenerEmpleadoDocIdentidad(@PathVariable String docIdentidad) throws RecursoNoEncontradoException {
        return empleadoServicio.obtenerEmpleadoDocIdentidad(docIdentidad)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/eliminar/{docIdentidad}")
    @Operation(summary = "Eliminar un empleado por su documento de identidad")
    public ResponseEntity<String> eliminarEmpleadoPorDocIdentidad(@PathVariable String docIdentidad) throws RecursoNoEncontradoException {
        return empleadoServicio.eliminarEmpleado(docIdentidad).map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping("/actualizarContrasena/{numDocIdentidad}/{nuevaContrasena}")
    @Operation(summary = "Actualizar contrasena del empleado segun su numero de documento identidad")
    public ResponseEntity<String> actualizarContrasena(@PathVariable String numDocIdentidad, @PathVariable String nuevaContrasena) throws RecursoNoEncontradoException {
        return empleadoServicio.actualizarContrasena(numDocIdentidad,nuevaContrasena)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping("/actualizarCelular/{numDocIdentidad}/{numCelular}")
    @Operation(summary = "Actualizar numero celular del empleado segun su numero de documento identidad")
    public ResponseEntity<String> actualizarNumeroCelular(@PathVariable String numDocIdentidad, @PathVariable String numCelular) throws RecursoNoEncontradoException {
        return empleadoServicio.actualizarNumeroCelular(numDocIdentidad,numCelular)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping("/actualizarNivelEscolar/{numDocIdentidad}/{nombreNivelEscolar}")
    @Operation(summary = "Actualizar nivel de escolaridad del empleado segun su numero de documento identidad")
    public ResponseEntity<String> actualizarNivelEscolaridad(@PathVariable String numDocIdentidad, @PathVariable String nombreNivelEscolar) throws RecursoNoEncontradoException {
        return empleadoServicio.actualizarNivelEscolaridad(numDocIdentidad,nombreNivelEscolar)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping("/actualizarEstadoActivo/{numDocIdentidad}/{estadoActivo}")
    @Operation(summary = "Actualizar el estado activo del empleado segun su numero de documento identidad")
    public ResponseEntity<String> actualizaractivo(@PathVariable String numDocIdentidad, @PathVariable Boolean estadoActivo) throws RecursoNoEncontradoException {
        return empleadoServicio.actualizaractivo(numDocIdentidad,estadoActivo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

}
