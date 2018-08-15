package com.library.book.poc.exception;

public class BookDoesNotExistsException extends RuntimeException {

    public BookDoesNotExistsException() {
        super();
    }

    public BookDoesNotExistsException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public BookDoesNotExistsException(final String message) {
        super(message);
    }

    public BookDoesNotExistsException(final Throwable cause) {
        super(cause);
    }

}
