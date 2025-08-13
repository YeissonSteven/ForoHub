package com.Alura.Foro.Hub.infra.exceptions;

import com.Alura.Foro.Hub.domain.ValidacionTopico;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GestorDeErrores {
    @ExceptionHandler(ValidacionTopico.class)
    public ResponseEntity gestionarErrorDeValidacion(ValidacionTopico ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
