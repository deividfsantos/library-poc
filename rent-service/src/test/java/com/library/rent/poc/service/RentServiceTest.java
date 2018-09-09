package com.library.rent.poc.service;

import com.library.rent.poc.client.BookClient;
import com.library.rent.poc.client.UserClient;
import com.library.rent.poc.client.response.Book;
import com.library.rent.poc.entity.RentEntity;
import com.library.rent.poc.exception.BookStockEmptyException;
import com.library.rent.poc.exception.NotFoundException;
import com.library.rent.poc.repository.RentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.beans.SamePropertyValuesAs.samePropertyValuesAs;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class RentServiceTest {

    private RentService rentService;
    private RentRepository rentRepository;
    private BookClient bookClient;
    private UserClient userClient;

    public RentServiceTest() {
        this.rentRepository = mock(RentRepository.class);
        this.bookClient = mock(BookClient.class);
        this.userClient = mock(UserClient.class);
        this.rentService = new RentService(rentRepository, bookClient, userClient);
    }

    @Test
    public void rentABookSuccessTest() {
        Book book = new Book("Author Test", "Title Test", 5);
        when(bookClient.getBookByTitle("Title Test")).thenReturn(book);
        when(userClient.getUserByCpf("123")).thenReturn(null);
        when(rentRepository.save(any())).thenReturn(new RentEntity("98765432100", "123", "Title Test"));

        doNothing().when(bookClient).updateBookByName(book);

        String code = rentService.rentABook("123", "Title Test");

        assertEquals("98765432100", code);
    }

    @Test
    public void rentABookNoStockExceptionTest() {
        Book book = new Book("Author Test", "Title Test", 0);
        when(bookClient.getBookByTitle("Title Test")).thenReturn(book);
        when(userClient.getUserByCpf("123")).thenReturn(null);

        BookStockEmptyException exception = assertThrows(BookStockEmptyException.class,
                () -> rentService.rentABook("123", "Title Test"));
        assertEquals("Book stock is empty.", exception.getMessage());
    }

    @Test
    public void returnBookSuccessTest() {
        Book book = new Book("Author Test", "Title Test", 5);
        when(rentRepository.findById("123")).thenReturn(Optional.of(new RentEntity("123", "Cpf Test", "Title Test")));
        doNothing().when(rentRepository).deleteById("123");
        when(bookClient.getBookByTitle("Title Test")).thenReturn(book);
        doNothing().when(bookClient).updateBookByName(book);

        Integer stock = rentService.returnBook("123");
        assertEquals(6, stock.intValue());
    }

    @Test
    public void returnBookNotFoundExceptionTest() {
        when(rentRepository.findById("123")).thenReturn(Optional.empty());
        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> rentService.returnBook("123"));
        assertEquals("Rent not found.", exception.getMessage());
    }

    @Test
    public void getRentsByCpf() {
        when(rentRepository.findAllByCpf("123")).thenReturn(buildRentList());

        List<RentEntity> rentsByCpf = rentService.getRentsByCpf("123");
        assertThat(rentsByCpf.get(0), samePropertyValuesAs(buildRentList().get(0)));
        assertThat(rentsByCpf.get(1), samePropertyValuesAs(buildRentList().get(1)));
        assertThat(rentsByCpf.get(2), samePropertyValuesAs(buildRentList().get(2)));
    }

    private List<RentEntity> buildRentList() {
        RentEntity rentEntity1 = new RentEntity("74", "123", "Title Test 1");
        RentEntity rentEntity2 = new RentEntity("85", "123", "Title Test 2");
        RentEntity rentEntity3 = new RentEntity("96", "123", "Title Test 3");
        return Arrays.asList(rentEntity1, rentEntity2, rentEntity3);
    }
}