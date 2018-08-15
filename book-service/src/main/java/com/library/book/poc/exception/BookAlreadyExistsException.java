package com.library.book.poc.exception;

public class BookAlreadyExistsException extends RuntimeException {

    public BookAlreadyExistsException() {
        super();
    }

    public BookAlreadyExistsException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public BookAlreadyExistsException(final String message) {
        super(message);
    }

    public BookAlreadyExistsException(final Throwable cause) {
        super(cause);
    }


}
