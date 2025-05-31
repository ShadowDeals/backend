package com.shadow.deals.exception;

import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * This class describes the response body that will be returned to the client in case of an error.
 *
 * @author Kirill "Tamada" Simovin
 */
@Getter
@ToString
@Serdeable
@RequiredArgsConstructor
public class APIExceptionResponse {
    /**
     * The text of the error that occurred.
     */
    private final String message;

    /**
     * Integer representation of the HTTP error code.
     */
    private final int code;
}
