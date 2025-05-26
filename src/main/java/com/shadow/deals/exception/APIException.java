package com.shadow.deals.exception;

import io.micronaut.http.HttpStatus;
import lombok.Getter;
import lombok.ToString;

/**
 * This class is a custom error class and is used to throw errors with an HTTP code. It is used as the main error class,
 * unless need to use a more narrowly focused class.
 *
 * @author Kirill "Tamada" Simovin
 */
@Getter
@ToString
public class APIException extends RuntimeException {
    /**
     * An object of the {@link HttpStatus} class is the HTTP code with which the exception is thrown.
     */
    private final HttpStatus code;

    /**
     * The constructor of the {@link APIException} class, which sets the exception object's message and code.
     *
     * @param message exception message.
     * @param code    HTTP exception code.
     */
    public APIException(String message, HttpStatus code) {
        super(message);
        this.code = code;
    }
}