package br.com.alura.comex.exception;

import static java.lang.String.format;

public class NotFoundException extends RuntimeException {

    private String message;
    private String error;

    public String getError() {
        return error;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public NotFoundException(String message, String error) {
        super();
        this.message = message;
        this.error = error;
    }

    public static NotFoundException notFoundException(Long id) {
        return new NotFoundException(format("Erro ao recuperar o objeto com id: %s", id), "Not Found");
    }
}
