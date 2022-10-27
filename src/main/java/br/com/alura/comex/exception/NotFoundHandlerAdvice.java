package br.com.alura.comex.exception;

import br.com.alura.comex.dto.GenericApiError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class NotFoundHandlerAdvice {

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public GenericApiError handle(NotFoundException exception) {
        return GenericApiError.Builder()
                .erro(exception.getError())
                .message(exception.getMessage())
                .build();
    }
}
