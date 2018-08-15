package com.library.book.poc.service;

import com.library.book.poc.dto.Book;
import com.library.book.poc.exception.BookAlreadyExistsException;
import com.library.book.poc.exception.BookDoesNotExistsException;
import com.library.book.poc.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public String insert(Book book) {
        if (bookRepository.getByTitle(book.getTitle()) != null) {
            throw new BookAlreadyExistsException("A book with this name already exists.");
        }
        return bookRepository.insert(book);
    }

    public List<Book> getAll() {
        return bookRepository.get();
    }

    public Book getByTitle(String title) {
        Book book = bookRepository.getByTitle(title);
        if (book == null) {
            throw new BookDoesNotExistsException("No books with this name were found.");
        }
        return book;
    }

    public Book update(Book book, String title) {
        Book bookFind = bookRepository.getByTitle(title);

        if (bookFind == null) {
            throw new BookDoesNotExistsException("No books with this name were found.");
        }

        bookRepository.update(book, title);
        return book;
    }

    public String delete(String title) {
        Book book = bookRepository.getByTitle(title);

        if (book == null) {
            throw new BookDoesNotExistsException("No books with this name were found.");
        }

        bookRepository.delete(title);
        return title;
    }
}
