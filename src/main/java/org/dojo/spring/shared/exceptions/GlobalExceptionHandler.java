package org.dojo.spring.shared.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;

@ControllerAdvice
@ResponseStatus
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetailsInResponse> resourceNotFoundException(ResourceNotFoundException resourceNotFoundException, WebRequest request) {
        var errorDetails = new ErrorDetailsInResponse(HttpStatus.NOT_FOUND, OffsetDateTime.now(), resourceNotFoundException.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetailsInResponse> globalExceptionHandler(Exception generalException, WebRequest request) {
        var errorDetails = new ErrorDetailsInResponse(HttpStatus.INTERNAL_SERVER_ERROR, OffsetDateTime.now(), generalException.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
