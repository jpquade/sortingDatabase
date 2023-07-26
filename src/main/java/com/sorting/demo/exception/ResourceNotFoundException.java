package com.sorting.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception used when a sorting id is not found for updating
 * a database entry
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    /**
     * Instantiates a new Resource not found exception.
     *
     * @param message is sent when the exception is caught
     */
    public ResourceNotFoundException(String message){
        super(message);
    }
}
