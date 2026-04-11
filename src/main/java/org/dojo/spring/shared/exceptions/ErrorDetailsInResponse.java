package org.dojo.spring.shared.exceptions;

import org.springframework.http.HttpStatus;

import java.time.OffsetDateTime;

public record ErrorDetailsInResponse(HttpStatus status, OffsetDateTime timestamp, String message, String details) { }
