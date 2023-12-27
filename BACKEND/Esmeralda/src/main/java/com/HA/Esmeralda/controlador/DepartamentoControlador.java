package com.HA.Esmeralda.controlador;

import com.HA.Esmeralda.dto.DepartamentoDto;
import com.HA.Esmeralda.exceptions.DuplicadoException;
import com.HA.Esmeralda.exceptions.RecursoNoEncontradoException;
import com.HA.Esmeralda.servicio.DepartamentoServicio;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departamentos")
@Tag(name = "Departamentos")
@Slf4j
public class DepartamentoControlador {

    @Autowired
    private DepartamentoServicio departamentoServicio;

    @Autowired
    ObjectMapper mapper;

    @PostMapping("/guardar")
    @Operation(summary = "Guardar un departamento")
    public ResponseEntity<String> guardarDepartamento(@RequestBody DepartamentoDto departamentoDto) throws DuplicadoException {
        return departamentoServicio.crearDepartamento(departamentoDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.internalServerError().build());
    }
    @GetMapping("/listarTodos")
    @Operation(summary = "Obtener todos los departamentos")
    public ResponseEntity<List<DepartamentoDto>> listarTodos() {
        return ResponseEntity.ok(departamentoServicio.listarTodos());
    }

    @GetMapping("/obtenerPorNombre/{nombreDepartamento}")
    @Operation(summary = "Obtener departamento por nombre")
    public ResponseEntity<DepartamentoDto> obtenerPorNombre(@PathVariable String nombreDepartamento) throws RecursoNoEncontradoException {
        return departamentoServicio.obtenerPorNombre(nombreDepartamento)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/eliminar/{departamento}")
    @Operation(summary = "Eliminar departamento por su nombre")
    public ResponseEntity<String> eliminarDepartamento(@PathVariable String departamento) throws RecursoNoEncontradoException {
        return departamentoServicio.eliminarDepartamento(departamento)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

}
