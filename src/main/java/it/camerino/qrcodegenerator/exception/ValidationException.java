package it.camerino.qrcodegenerator.exception;

import org.springframework.http.HttpStatus;

public class ValidationException extends BaseException{

    public ValidationException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }

    public ValidationException(String message, HttpStatus status) {
        super(message, status);
    }
}
