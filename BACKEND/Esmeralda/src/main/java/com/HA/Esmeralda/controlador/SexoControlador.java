package com.HA.Esmeralda.controlador;

import com.HA.Esmeralda.dto.NivelEscolaridadDto;
import com.HA.Esmeralda.dto.SexoDto;
import com.HA.Esmeralda.servicio.SexoServicio;
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
    public ResponseEntity<String> guardarSexo(@RequestBody SexoDto sexoDto) {

        ResponseEntity<String> response = null;
        Optional<String> mensaje = sexoServicio.crearSexo(sexoDto);

        if (sexoDto!=null) {
            response = ResponseEntity.ok(mensaje.get());
        } else {
            response = ResponseEntity.internalServerError().build();
        }
        return response;
    }

    @GetMapping("/listarTodos")
    @Operation(summary = "Obtener todos los sexos")
    public ResponseEntity<List<SexoDto>> listarTodos() {
        return ResponseEntity.ok(sexoServicio.listarTodos());
    }

    @DeleteMapping("/eliminar/{sexo}")
    @Operation(summary = "Eliminar sexo por su nombre")
    public ResponseEntity<String> eliminarSexo(@PathVariable String sexo) {
        return sexoServicio.eliminarSexo(sexo).map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }
}
