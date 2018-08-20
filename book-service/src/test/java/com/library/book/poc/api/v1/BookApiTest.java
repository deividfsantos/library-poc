package com.library.book.poc.api.v1;

import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import com.library.book.poc.Main;
import com.library.book.poc.dto.Book;
import com.library.book.poc.exception.BookAlreadyExistsException;
import com.library.book.poc.exception.BookDoesNotExistsException;
import com.library.book.poc.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = Main.class)
@Provider("book-service")
@PactFolder("../resources/pacts")
public class BookApiTest {

    @MockBean
    private BookService bookService;

    @BeforeEach
    public void setupTestTarget(PactVerificationContext context) {
        context.setTarget(new HttpTestTarget("localhost", 8081, "/"));
    }

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    public void pactVerificationTestTemplate(PactVerificationContext context) {
        context.verifyInteraction();
    }

    @State("accept a new book")
    public void toCreateNewBook() {
        when(bookService.insert(any(Book.class))).thenReturn("Harry Potter");
    }

    @State("insert an existing book")
    public void toCreateAnExistingBookException() {
        when(bookService.insert(any(Book.class)))
                .thenThrow(new BookAlreadyExistsException("A book with this name already exists."));
    }

    @State("get all books")
    public void toGetAllBooks() {
        List<Book> books = Arrays.asList(
                new Book("Author Test 1", "Title Test 1", "15"),
                new Book("Author Test 2", "Title Test 2", "4"),
                new Book("Author Test 3", "Title Test 3", "3")
        );

        when(bookService.getAll()).thenReturn(books);
    }

    @State("get a book by name")
    public void toGetABookByName() {
        when(bookService.getByTitle("Harry Potter"))
                .thenReturn(new Book("J.K. Rowling", "Harry Potter", "15"));
    }

    @State("get a book not found by name")
    public void toGetABookNotFoundByName() {
        when(bookService.getByTitle("Harry Potter"))
                .thenThrow(new BookDoesNotExistsException("No books with this name were found."));
    }

    @State("update a Book")
    public void toUpdateABookByName() {
        when(bookService.update(any(Book.class), eq("Harry Potter")))
                .thenReturn(new Book("J.K. Rowling", "Harry Potter", "15"));
    }

    @State("update a not found Book")
    public void toUpdateABookNotFoundException() {
        when(bookService.update(any(Book.class), eq("Harry Potter")))
                .thenThrow(new BookDoesNotExistsException("No books with this name were found."));
    }

    @State("delete a book by name")
    public void toDeleteABookByName() {
        when(bookService.delete("Harry Potter"))
                .thenReturn("Harry Potter");
    }

    @State("delete a not found book")
    public void toDeleteABookNotFoundException() {
        when(bookService.delete("Harry Potter"))
                .thenThrow(new BookDoesNotExistsException("No books with this name were found."));
    }
}