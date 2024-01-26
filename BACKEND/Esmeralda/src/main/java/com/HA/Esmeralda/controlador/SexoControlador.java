package com.HA.Esmeralda.controlador;

import com.HA.Esmeralda.dto.SexoDto;
import com.HA.Esmeralda.exceptions.DuplicadoException;
import com.HA.Esmeralda.exceptions.RecursoNoEncontradoException;
import com.HA.Esmeralda.servicio.SexoServicio;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sexos")
@Tag(name = "Sexos")
@Slf4j
public class SexoControlador {
    @Autowired
    private SexoServicio sexoServicio;

    @Autowired
    ObjectMapper mapper;

    @PostMapping("/guardar")
    @Operation(summary = "Guardar un tipo de sexo")
    public ResponseEntity<String> guardarSexo(@RequestBody SexoDto sexoDto) throws DuplicadoException {
        return sexoServicio.crearSexo(sexoDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.internalServerError().build());
    }

    @GetMapping("/listarTodos")
    @Operation(summary = "Obtener todos los sexos")
    public ResponseEntity<List<SexoDto>> listarTodos() {
        return ResponseEntity.ok(sexoServicio.listarTodos());
    }

    @GetMapping("/obtenerPorNombre/{nombreSexo}")
    @Operation(summary = "Obtener sexo por nombre")
    public ResponseEntity<SexoDto> obtenerPorNombre(@PathVariable String nombreSexo) throws RecursoNoEncontradoException {
        return sexoServicio.obtenerPorNombre(nombreSexo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/eliminar/{sexo}")
    @Operation(summary = "Eliminar sexo por su nombre")
    public ResponseEntity<String> eliminarSexo(@PathVariable String sexo) throws RecursoNoEncontradoException {
        return sexoServicio.eliminarSexo(sexo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }
}
