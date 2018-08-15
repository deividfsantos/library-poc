package com.library.rent.poc.config;

import com.library.rent.poc.exception.BookStockEmptyException;
import com.library.rent.poc.exception.Error;
import com.library.rent.poc.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookStockEmptyException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public Error handleBookAlreadyExists(BookStockEmptyException exception) {
        return new Error(exception.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public Error handleUserNotFoundException(NotFoundException exception) {
        return new Error(exception.getMessage());
    }
}
