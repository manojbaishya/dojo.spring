package org.dojo.spring.shared.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException() { }
    public ResourceNotFoundException(String message) { super(message); }
    public ResourceNotFoundException(Throwable cause) { super(cause); }
    public ResourceNotFoundException(String message, Throwable cause) { super(message, cause); }
    public ResourceNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
