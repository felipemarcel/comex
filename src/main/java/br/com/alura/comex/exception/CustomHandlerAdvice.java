package br.com.alura.comex.exception;

import br.com.alura.comex.dto.GenericApiError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class CustomHandlerAdvice {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<GenericApiError> handle(MethodArgumentNotValidException exception) {
        return exception
                .getBindingResult()
                .getFieldErrors()
                .stream().map(fieldError -> {
                    String message = messageSource.getMessage(fieldError, getLocale());
                    return GenericApiError
                            .Builder()
                            .erro(message)
                            .campo(fieldError.getField())
                            .build();
                }).toList();
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public GenericApiError handle(DataIntegrityViolationException exception) {
        return GenericApiError
                .Builder()
                .erro(exception.getLocalizedMessage())
                .message(exception.getMostSpecificCause().getMessage())
                .build();
    }
}
