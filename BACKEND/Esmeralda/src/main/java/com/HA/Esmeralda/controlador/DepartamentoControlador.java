package com.HA.Esmeralda.controlador;

import com.HA.Esmeralda.dto.DepartamentoDto;
import com.HA.Esmeralda.servicio.DepartamentoServicio;
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
    public ResponseEntity<String> guardarDepartamento(@RequestBody DepartamentoDto departamentoDto) {

        ResponseEntity<String> response = null;
        Optional<String> mensaje = departamentoServicio.crearDepartamento(departamentoDto);

        if (departamentoDto!=null) {
            response = ResponseEntity.ok(mensaje.get());
        } else {
            response = ResponseEntity.internalServerError().build();
        }
        return response;
    }
}
