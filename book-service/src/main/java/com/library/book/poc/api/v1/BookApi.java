package com.library.book.poc.api.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.book.poc.output.v1.BookOutput;
import com.library.book.poc.service.BookService;
import com.library.book.poc.dto.Book;
import com.library.book.poc.input.BookInput;
import com.library.book.poc.output.v1.TitleOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.UnknownHostException;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/v1/books")
public class BookApi {

    @Autowired
    private BookService bookService;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping
    public ResponseEntity<?> insertABook(@RequestBody BookInput bookInput) throws Exception {
        String bookTitle = bookService.insert(objectMapper.convertValue(bookInput, Book.class));
        return ok(new TitleOutput(bookTitle));
    }

    @GetMapping
    public ResponseEntity<?> getAllBooks() {
        List<Book> books = bookService.getAll();
        return ok(books.stream().map(book -> objectMapper.convertValue(book, BookOutput.class)));
    }

    @GetMapping("/{title}")
    public ResponseEntity<?> getOneBook(@PathVariable String title) throws Exception {
        return ok(objectMapper.convertValue(bookService.getByTitle(title), BookOutput.class));
    }

    @PutMapping("/{title}")
    public ResponseEntity<?> updateBook(@PathVariable String title, @RequestBody BookInput bookInput) {
        Book book = bookService.update(objectMapper.convertValue(bookInput, Book.class), title);
        return ok(objectMapper.convertValue(book, BookOutput.class));
    }

    @DeleteMapping("/{title}")
    public ResponseEntity<?> deleteBook(@PathVariable String title) throws UnknownHostException {
        String bookTitle = bookService.delete(title);
        return ok(new TitleOutput(bookTitle));
    }
}
