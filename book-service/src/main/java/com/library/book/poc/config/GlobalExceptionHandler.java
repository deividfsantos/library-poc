package com.library.book.poc.config;

import com.library.book.poc.exception.BookAlreadyExistsException;
import com.library.book.poc.exception.BookDoesNotExistsException;
import com.library.book.poc.exception.Erro;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookAlreadyExistsException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public Erro handleBookAlreadyExists(BookAlreadyExistsException exception) {
        return new Erro(exception.getMessage());
    }

    @ExceptionHandler(BookDoesNotExistsException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public Erro handleBookDoesNotExists(BookDoesNotExistsException exception) {
        return new Erro(exception.getMessage());
    }

}
