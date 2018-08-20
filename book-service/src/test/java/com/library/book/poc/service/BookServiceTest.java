package com.library.book.poc.service;


import com.library.book.poc.dto.Book;
import com.library.book.poc.exception.BookAlreadyExistsException;
import com.library.book.poc.exception.BookDoesNotExistsException;
import com.library.book.poc.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.beans.SamePropertyValuesAs.samePropertyValuesAs;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class BookServiceTest {

    private BookService bookService;
    private BookRepository bookRepository;

    private String title;

    public BookServiceTest() {
        this.bookRepository = mock(BookRepository.class);
        this.bookService = new BookService(bookRepository);
    }

    @Test
    public void insertBookSuccessTest() {
        Book book = buildBook();
        String title = "Harry Potter and the chamber of secrets";
        when(bookRepository.getByTitle(title)).thenReturn(null);
        when(bookRepository.insert(book)).thenReturn(title);
        assertEquals(title, bookService.insert(book));
    }

    @Test
    public void insertBookAlreadyExistsExceptionTest() {
        Book book = buildBook();
        String title = "Harry Potter and the chamber of secrets";
        when(bookRepository.getByTitle(title)).thenReturn(book);
        when(bookRepository.insert(book)).thenReturn(title);
        BookAlreadyExistsException exception = assertThrows(BookAlreadyExistsException.class, () -> bookService.insert(book));
        assertEquals("A book with this name already exists.", exception.getMessage());
    }

    @Test
    public void getAllBooksSuccessTest() throws Exception {
        when(bookRepository.get()).thenReturn(buildBookList());
        List<Book> books = bookService.getAll();
        assertThat(books.get(0), samePropertyValuesAs(buildBookList().get(0)));
        assertThat(books.get(1), samePropertyValuesAs(buildBookList().get(1)));
        assertThat(books.get(2), samePropertyValuesAs(buildBookList().get(2)));
    }

    private List<Book> buildBookList() {
        Book book1 = new Book("Author Test1", "Title Test1", "123");
        Book book2 = new Book("Author Test2", "Title Test2", "321");
        Book book3 = new Book("Author Test3", "Title Test3", "132");
        return Arrays.asList(book1, book2, book3);
    }

    @Test
    public void getBookByTitleSuccessTest() throws Exception {
        when(bookRepository.getByTitle("Harry Potter and the chamber of secrets")).thenReturn(buildBook());
        Book book = bookService.getByTitle("Harry Potter and the chamber of secrets");
        assertThat(book, samePropertyValuesAs(buildBook()));
    }

    @Test
    public void getBookByTitleNotExistsTest() throws Exception {
        String title = "Harry Potter and the chamber of secrets";
        when(bookRepository.getByTitle(title)).thenReturn(null);

        BookDoesNotExistsException exception = assertThrows(BookDoesNotExistsException.class,
                () -> bookService.getByTitle(title));
        assertEquals("No books with this name were found.", exception.getMessage());
    }

    @Test
    public void updateBookSuccessTest() throws Exception {
        String title = "Harry Potter and the chamber of secrets";
        doNothing().when(bookRepository).update(buildBook(), title);
        when(bookRepository.getByTitle(title)).thenReturn(buildBook());
        Book book = bookService.update(buildBook(), title);
        assertThat(buildBook(), samePropertyValuesAs(book));
    }

    @Test
    public void updateBookNotExistsTest() throws Exception {
        String title = "Harry Potter and the chamber of secrets";
        doNothing().when(bookRepository).update(buildBook(), title);
        when(bookRepository.getByTitle(title)).thenReturn(null);

        BookDoesNotExistsException exception = assertThrows(BookDoesNotExistsException.class,
                () -> bookService.update(buildBook(), title));
        assertEquals("No books with this name were found.", exception.getMessage());
    }

    @Test
    public void deleteBookSuccessTest() throws Exception {
        String title = "Harry Potter and the chamber of secrets";
        doNothing().when(bookRepository).delete(title);
        when(bookRepository.getByTitle(title)).thenReturn(buildBook());
        String titleBook = bookService.delete(title);
        assertEquals(title, titleBook);
    }

    @Test
    public void deleteBookNotExistsTest() throws Exception {
        String title = "Harry Potter and the chamber of secrets";
        doNothing().when(bookRepository).delete(title);
        when(bookRepository.getByTitle(title)).thenReturn(null);

        BookDoesNotExistsException exception = assertThrows(BookDoesNotExistsException.class,
                () -> bookService.delete(title));
        assertEquals("No books with this name were found.", exception.getMessage());
    }

    private Book buildBook() {
        return new Book("JK Rowling", "Harry Potter and the chamber of secrets", "132");
    }

}