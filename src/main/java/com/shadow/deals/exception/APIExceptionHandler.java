package com.shadow.deals.exception;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;
import org.jetbrains.annotations.NotNull;

/**
 * This class is an interceptor for all exceptions thrown during the operation of the application.
 *
 * @author Kirill "Tamada" Simovin
 */
@Singleton
@Requires(classes = {Exception.class})
@Produces(MediaType.APPLICATION_JSON)
public class APIExceptionHandler implements ExceptionHandler<Exception, HttpResponse<?>> {
    /**
     * This method is responsible for handling thrown exceptions.
     *
     * @param request   the request during which the exception occurred.
     * @param exception the exception that occurred.
     * @return {@link HttpResponse}, the status of which corresponds to the status of the thrown exception
     * (if it is {@link APIException}) or is {@link HttpStatus#INTERNAL_SERVER_ERROR}, and the body is an object of the
     * {@link APIExceptionResponse} class, indicating the error text and an integer representation of the HTTP error
     * code.
     */
    @Override
    public HttpResponse<?> handle(HttpRequest request, @NotNull Exception exception) {
//        exception.printStackTrace();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        if (exception instanceof APIException apiException) {
            status = apiException.getCode();
        }

        return HttpResponse.status(status).body(new APIExceptionResponse(exception.getMessage(), status.getCode()));
    }
}
