package com.library.user.poc.config;

import com.library.user.poc.exception.Error;
import com.library.user.poc.exception.UserAlreadyExistsException;
import com.library.user.poc.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public Error handleBookAlreadyExists(UserAlreadyExistsException exception) {
        return new Error(exception.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public Error handleBookDoesNotExists(UserNotFoundException exception) {
        return new Error(exception.getMessage());
    }

}
