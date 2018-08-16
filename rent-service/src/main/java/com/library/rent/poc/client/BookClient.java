package com.library.rent.poc.client;

import com.library.rent.poc.client.response.Book;
import com.library.rent.poc.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class BookClient {

    private RestTemplate restTemplate;

    @Value("${url.bookservice}")
    private String bookUrl;

    public BookClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Book getBookByTitle(String title) {
        try {
            return restTemplate.getForObject(bookUrl + title, Book.class);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR) {
                throw new NotFoundException("Book not found in this library.");
            }
            throw e;
        }
    }

    public void updateBookByName(Book book) {
        restTemplate.put(bookUrl + book.getTitle(), book);
    }
}
