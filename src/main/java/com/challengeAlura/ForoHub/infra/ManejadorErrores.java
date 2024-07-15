package com.challengeAlura.ForoHub.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ManejadorErrores {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValid(MethodArgumentNotValidException ex) {
        var errores = ex.getFieldErrors().stream().map(DetallesError::new).toList();
        return ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler(ExcepcionCrearTopico.class)
    public ResponseEntity errorCrearTopico(ExcepcionCrearTopico ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity entityNotFound(EntityNotFoundException ex) {
        return ResponseEntity.notFound().build();
    }

    private record DetallesError(
            String campo,
            String error
    ){
        public DetallesError(FieldError fieldError) {
            this(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }
}