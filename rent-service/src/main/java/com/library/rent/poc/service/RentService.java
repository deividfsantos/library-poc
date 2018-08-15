package com.library.rent.poc.service;

import com.library.rent.poc.client.BookClient;
import com.library.rent.poc.client.UserClient;
import com.library.rent.poc.client.response.Book;
import com.library.rent.poc.entity.RentEntity;
import com.library.rent.poc.exception.BookStockEmptyException;
import com.library.rent.poc.exception.NotFoundException;
import com.library.rent.poc.repository.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentService {

    @Autowired
    private RentRepository rentRepository;

    @Autowired
    private BookClient bookClient;

    @Autowired
    private UserClient userClient;

    public String rentABook(String cpf, String bookTitle) {
        Book book = bookClient.getBookByTitle(bookTitle);
        userClient.getUserByCpf(cpf);

        if (book.getStock() <= 0) {
            throw new BookStockEmptyException("Book stock is empty.");
        }

        RentEntity rent = rentRepository.save(new RentEntity(cpf, bookTitle));

        book.setStock(book.getStock() - 1);
        bookClient.updateBookByName(book);

        return rent.getCode();
    }

    public Integer returnBook(String receipt) {
        return rentRepository.findById(receipt).map(rent -> {
            rentRepository.deleteById(receipt);
            Book book = bookClient.getBookByTitle(rent.getBookName());
            book.setStock(book.getStock() + 1);
            bookClient.updateBookByName(book);
            return book.getStock();
        }).orElseThrow(() -> new NotFoundException("Rent not found."));
    }

    public List<RentEntity> getRentsByCpf(String cpf) {
        return rentRepository.findAllByCpf(cpf);
    }
}
