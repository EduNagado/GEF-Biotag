package com.gef_biotag.Biotag.exceptional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 404 - Not Found
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(EntityNotFoundException ex) {
        var response = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                "Recurso não encontrado",
                LocalDateTime.now(),
                List.of(ex.getMessage())
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    // 400 - Bad Request (validação de argumentos)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(MethodArgumentNotValidException ex) {
        List<String> detalhes = ex.getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.toList());

        var response = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Erro de validação",
                LocalDateTime.now(),
                detalhes
        );

        return ResponseEntity.badRequest().body(response);
    }

    // 400 - Bad Request (validação de constraints diretas)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(ConstraintViolationException ex) {
        List<String> detalhes = ex.getConstraintViolations()
                .stream()
                .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                .collect(Collectors.toList());

        var response = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Violação de restrição",
                LocalDateTime.now(),
                detalhes
        );

        return ResponseEntity.badRequest().body(response);
    }

    // 500 - Internal Server Error (erro genérico)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralError(Exception ex) {
        var response = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro interno no servidor",
                LocalDateTime.now(),
                List.of(ex.getMessage())
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    // Classe para resposta padronizada de erro
    public record ErrorResponse(
            int status,
            String mensagem,
            LocalDateTime timestamp,
            List<String> detalhes
    ) {}
}
