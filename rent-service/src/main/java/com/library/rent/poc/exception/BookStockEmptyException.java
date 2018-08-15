package com.library.rent.poc.exception;

public class BookStockEmptyException extends RuntimeException {

    public BookStockEmptyException() {
        super();
    }

    public BookStockEmptyException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public BookStockEmptyException(final String message) {
        super(message);
    }

    public BookStockEmptyException(final Throwable cause) {
        super(cause);
    }


}