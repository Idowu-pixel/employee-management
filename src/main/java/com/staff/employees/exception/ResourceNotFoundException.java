package com.staff.employees.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    private String resourceName;
    private String fieldName;
    private String field;
    private Long fieldId;

    public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(String message, String resourceName, String fieldName, String field) {
        super(message);
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.field = field;
    }

    public ResourceNotFoundException(String message, String resourceName, String field, Long fieldId) {
        super(message);
        this.resourceName = resourceName;
        this.field = field;
        this.fieldId = fieldId;
    }
}
