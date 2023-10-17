package com.HA.Esmeralda.controlador;

import com.HA.Esmeralda.dto.NivelEscolaridadDto;
import com.HA.Esmeralda.servicio.NivelEscolaridadServicio;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
