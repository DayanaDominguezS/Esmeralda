package com.HA.Esmeralda.controlador;

import com.HA.Esmeralda.dto.EmpleadoDto;
import com.HA.Esmeralda.servicio.EmpleadoServicio;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<String> guardarEmpleado(@RequestBody EmpleadoDto empleadoDto) {

        ResponseEntity<String> response = null;
        Optional<String> mensaje = empleadoServicio.crearEmpleado(empleadoDto);

        if (empleadoDto!=null) {
            response = ResponseEntity.ok(mensaje.get());
        } else {
            response = ResponseEntity.internalServerError().build();
        }
        return response;

    }

}
