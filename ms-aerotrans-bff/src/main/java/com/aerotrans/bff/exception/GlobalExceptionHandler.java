package com.aerotrans.bff.exception;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(WebClientResponseException.NotFound.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(WebClientResponseException.NotFound ex) {
        return buildError(HttpStatus.NOT_FOUND, "Traslado no encontrado");
    }

    @ExceptionHandler(WebClientResponseException.BadRequest.class)
    public ResponseEntity<Map<String, Object>> handleBadRequest(WebClientResponseException.BadRequest ex) {
        return buildError(HttpStatus.BAD_REQUEST, "Solicitud inválida");
    }

    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<Map<String, Object>> handleWebClient(WebClientResponseException ex) {
        return buildError(HttpStatus.BAD_GATEWAY, "Error al comunicarse con el microservicio");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneral(Exception ex) {
        return buildError(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno del servidor");
    }

    private ResponseEntity<Map<String, Object>> buildError(HttpStatus status, String message) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", Instant.now());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", message);
        return ResponseEntity.status(status).body(body);
    }
}
