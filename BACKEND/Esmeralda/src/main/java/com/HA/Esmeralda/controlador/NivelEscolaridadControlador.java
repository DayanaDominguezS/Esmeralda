package com.HA.Esmeralda.controlador;

import com.HA.Esmeralda.dto.NivelEscolaridadDto;
import com.HA.Esmeralda.exceptions.DuplicadoException;
import com.HA.Esmeralda.exceptions.RecursoNoEncontradoException;
import com.HA.Esmeralda.servicio.NivelEscolaridadServicio;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nivelesEscolaridad")
@Tag(name = "Niveles de escolaridad")
@Slf4j
public class NivelEscolaridadControlador {
    @Autowired
    private NivelEscolaridadServicio nivelEscolaridadServicio;

    @Autowired
    ObjectMapper mapper;

    @PostMapping("/guardar")
    @Operation(summary = "Guardar un departamento")
    public ResponseEntity<String> guardarNivelEscolaridad(@RequestBody NivelEscolaridadDto nivelEscolaridadDto) throws DuplicadoException {
        return nivelEscolaridadServicio.crearNivelEscolaridad(nivelEscolaridadDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.internalServerError().build());
    }

    @GetMapping("/listarTodos")
    @Operation(summary = "Obtener todos los niveles de escolaridad")
    public ResponseEntity<List<NivelEscolaridadDto>> listarTodos() {
        return ResponseEntity.ok(nivelEscolaridadServicio.listarTodos());
    }

    @GetMapping("/obtenerPorNombre/{nombreNivelEscolar}")
    @Operation(summary = "Obtener nivel escolar por nombre")
    public ResponseEntity<NivelEscolaridadDto> obtenerPorNombre(@PathVariable String nombreNivelEscolar) throws RecursoNoEncontradoException {
        return nivelEscolaridadServicio.obtenerPorNombre(nombreNivelEscolar)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/eliminar/{nivelEscolaridad}")
    @Operation(summary = "Eliminar nivel de escolaridad por su nombre")
    public ResponseEntity<String> eliminarNivelEscolaridad(@PathVariable String nivelEscolaridad) throws RecursoNoEncontradoException {
        return nivelEscolaridadServicio.eliminarNivelEscolaridad(nivelEscolaridad)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

}
