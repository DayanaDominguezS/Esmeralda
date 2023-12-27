package com.HA.Esmeralda.controlador;

import com.HA.Esmeralda.dto.TipoDocIdentidadDto;
import com.HA.Esmeralda.exceptions.DuplicadoException;
import com.HA.Esmeralda.exceptions.RecursoNoEncontradoException;
import com.HA.Esmeralda.servicio.TipoDocIdentidadServicio;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tiposDocIdentidad")
@Tag(name = "Tipos de Documentos de identidad")
@Slf4j
public class TipoDocIdentidadControlador {
    @Autowired
    private TipoDocIdentidadServicio tipoDocIdentidadServicio;

    @Autowired
    ObjectMapper mapper;

    @PostMapping("/guardar")
    @Operation(summary = "Guardar un tipo de documento de identidad")
    public ResponseEntity<String> guardarTipoDocIdentidad(@RequestBody TipoDocIdentidadDto tipoDocIdentidadDto) throws DuplicadoException {
        return tipoDocIdentidadServicio.crearTipoDocIdentidad(tipoDocIdentidadDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.internalServerError().build());
    }

    @GetMapping("/listarTodos")
    @Operation(summary = "Obtener todos los tipos de documento de identidad")
    public ResponseEntity<List<TipoDocIdentidadDto>> listarTodos() {
        return ResponseEntity.ok(tipoDocIdentidadServicio.listarTodos());
    }

    @GetMapping("/obtenerPorNombre/{nombreTipoDoc}")
    @Operation(summary = "Obtener tipo documento identidad por nombre")
    public ResponseEntity<TipoDocIdentidadDto> obtenerPorNombre(@PathVariable String nombreTipoDoc) throws RecursoNoEncontradoException {
        return tipoDocIdentidadServicio.obtenerPorNombre(nombreTipoDoc)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/eliminar/{tipoDocIdentidad}")
    @Operation(summary = "Eliminar tipo de documento de identidad por su nombre")
    public ResponseEntity<String> eliminarTipoDocIdentidad(@PathVariable String tipoDocIdentidad) throws RecursoNoEncontradoException {
        return tipoDocIdentidadServicio.eliminarTipoDocIdentidad(tipoDocIdentidad)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

}
