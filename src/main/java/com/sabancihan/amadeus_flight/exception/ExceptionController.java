package com.sabancihan.amadeus_flight.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.Instant;
import java.time.format.DateTimeFormatter;

@Slf4j
@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler({BaseException.class})
    public ResponseEntity<ErrorDTO> handleBaseException(HttpServletRequest request, BaseException ex) {
        return getErrorDTO(request, ex, ex.getCode(), ex.getTitle(),false);
    }


    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<ErrorDTO> handleSQLIntegrityConstraintViolationException(HttpServletRequest request, DataIntegrityViolationException ex) {
        return getErrorDTO(request, ex, 400, "Invalid reference parameter",true);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorDTO> handleBadRequestException(HttpServletRequest request, MethodArgumentNotValidException ex) {
        return getErrorDTO(request, ex, 400, "Invalid parameter",true);
    }

    private ResponseEntity<ErrorDTO> getErrorDTO(HttpServletRequest request, Exception ex, int code, String title,boolean hideMessage) {
        log.error("Error {} occurred while processing request: {},  message: {}", ex.getClass(), request.getRequestURI(), ex.getMessage());
        Instant now = Instant.now();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;
        String isoDateTime = formatter.format(now);

        return ResponseEntity
                .status(code)
                .body(ErrorDTO
                        .builder()
                        .title(title)
                        .message(hideMessage ? "" : ex.getMessage())
                        .method(request.getMethod())
                        .path(request.getRequestURI())
                        .timestamp(isoDateTime)
                        .build()
                );
    }
}
