package com.HA.Esmeralda.controlador;

import com.HA.Esmeralda.dto.TipoDocIdentidadDto;
import com.HA.Esmeralda.servicio.TipoDocIdentidadServicio;
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
    public ResponseEntity<String> guardarTipoDocIdentidad(@RequestBody TipoDocIdentidadDto tipoDocIdentidadDto) {

        ResponseEntity<String> response = null;
        Optional<String> mensaje = tipoDocIdentidadServicio.crearTipoDocIdentidad(tipoDocIdentidadDto);

        if (tipoDocIdentidadDto!=null) {
            response = ResponseEntity.ok(mensaje.get());
        } else {
            response = ResponseEntity.internalServerError().build();
        }
        return response;
    }

}
