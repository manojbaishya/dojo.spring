package org.dojo.spring.shared.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Date;

public class ErrorDetails {
    public ErrorDetails(Date timestamp, String message, String details) {
        this.setTimestamp(timestamp);
        this.setMessage(message);
        this.setDetails(details);
    }

    public ErrorDetails(HttpStatus status, Date timestamp, String message, String details) {
        this.setStatus(status);
        this.setDetails(details);
        this.setTimestamp(timestamp);
        this.setMessage(message);
    }

    private HttpStatus status;
    public HttpStatus getStatus() { return status; }
    public void setStatus(HttpStatus status) { this.status = status; }


    private Date timestamp;
    public Date getTimestamp() { return timestamp; }
    public void setTimestamp(Date timestamp) { this.timestamp = timestamp; }


    private String message;
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }


    private String details;
    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }
}
