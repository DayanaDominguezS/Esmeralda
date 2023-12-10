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
import java.util.Optional;

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

        ResponseEntity<String> response = null;
        Optional<String> mensaje = empleadoServicio.crearEmpleado(empleadoDto);

        if (empleadoDto!=null) {
            response = ResponseEntity.ok(mensaje.get());
        } else {
            response = ResponseEntity.internalServerError().build();
        }
        return response;
    }

    @GetMapping("/listarTodos")
    @Operation(summary = "Obtener todos los empleados")
    public ResponseEntity<List<EmpleadoDto>> listarTodos() {
        return ResponseEntity.ok(empleadoServicio.listarTodos());
    }

    @GetMapping("/listarPorDocIdentidad/{docIdentidad}")
    @Operation(summary = "Listar empleado por numero de documento de identidad")
    public ResponseEntity<EmpleadoDto> listarEmpleadoDocIdentidad(@PathVariable String docIdentidad) throws RecursoNoEncontradoException {
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

}
