package com.HA.Esmeralda.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptions {

    public static final String MENSAJE = "Lo sentimos, ha ocurrido un error!";


    @ExceptionHandler({RecursoNoEncontradoException.class})
    public ResponseEntity<MensajeError> procesarRecursoNoEncontrado(RecursoNoEncontradoException e){
        MensajeError mensajeError = new MensajeError();
        mensajeError.setMessage(MENSAJE);
        mensajeError.setDescription(e.getMessage());
        mensajeError.setStatusCode(1001);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensajeError);
    }

    @ExceptionHandler({AsignacionException.class})
    public ResponseEntity<MensajeError> procesarCategoriaAsignada(AsignacionException e){
        MensajeError mensajeError = new MensajeError();
        mensajeError.setMessage(MENSAJE);
        mensajeError.setDescription(e.getMessage());
        mensajeError.setStatusCode(1002);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensajeError);
    }

    @ExceptionHandler({DuplicadoException.class})
    public ResponseEntity<MensajeError> procesarDuplicadoException(DuplicadoException e){
        MensajeError mensajeError = new MensajeError();
        mensajeError.setMessage(MENSAJE);
        mensajeError.setDescription(e.getMessage());
        mensajeError.setStatusCode(1003);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensajeError);
    }

}
