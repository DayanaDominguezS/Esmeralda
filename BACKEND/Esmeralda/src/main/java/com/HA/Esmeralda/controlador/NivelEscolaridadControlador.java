package com.HA.Esmeralda.controlador;

import com.HA.Esmeralda.dto.DepartamentoDto;
import com.HA.Esmeralda.dto.NivelEscolaridadDto;
import com.HA.Esmeralda.servicio.NivelEscolaridadServicio;
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
    public ResponseEntity<String> guardarNivelEscolaridad(@RequestBody NivelEscolaridadDto nivelEscolaridadDto) {

        ResponseEntity<String> response = null;
        Optional<String> mensaje = nivelEscolaridadServicio.crearNivelEscolaridad(nivelEscolaridadDto);

        if (nivelEscolaridadDto!=null) {
            response = ResponseEntity.ok(mensaje.get());
        } else {
            response = ResponseEntity.internalServerError().build();
        }
        return response;
    }

    @GetMapping("/listarTodos")
    @Operation(summary = "Obtener todos los niveles de escolaridad")
    public ResponseEntity<List<NivelEscolaridadDto>> listarTodos() {
        return ResponseEntity.ok(nivelEscolaridadServicio.listarTodos());
    }

    @DeleteMapping("/eliminar/{nivelEscolaridad}")
    @Operation(summary = "Eliminar nivel de escolaridad por su nombre")
    public ResponseEntity<String> eliminarNivelEscolaridad(@PathVariable String nivelEscolaridad) {
        return nivelEscolaridadServicio.eliminarNivelEscolaridad(nivelEscolaridad).map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

}
