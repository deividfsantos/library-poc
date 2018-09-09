package com.library.rent.poc.api;

import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import com.library.rent.poc.Main;
import com.library.rent.poc.client.BookClient;
import com.library.rent.poc.client.UserClient;
import com.library.rent.poc.entity.RentEntity;
import com.library.rent.poc.output.RentOutput;
import com.library.rent.poc.service.RentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = Main.class)
@Provider("rent-service")
@PactFolder("../resources/pacts/rentapi")
public class RentApiTest {

    @MockBean
    private BookClient bookClient;

    @MockBean
    private UserClient userClient;

    @MockBean
    private RentService rentService;

    @BeforeEach
    public void setupTestTarget(PactVerificationContext context) {
        context.setTarget(new HttpTestTarget("localhost", 8081, "/"));
    }

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    public void pactVerificationTestTemplate(PactVerificationContext context) {
        context.verifyInteraction();
    }

    @State("rent a book")
    public void toRentABook() {
        when(rentService.rentABook("03459409591", "Harry Potter")).thenReturn("12345");
    }

    @State("get rents")
    public void toGetAllRentsByCpf() {
        List<RentEntity> rentEntities = new ArrayList<>();
        rentEntities.add(new RentEntity("123","03459409591","Harry Potter"));
        rentEntities.add(new RentEntity("124","03459409591","Maze Runner"));
        rentEntities.add(new RentEntity("321","03459409591","Sapiens"));
        when(rentService.getRentsByCpf("03459409591")).thenReturn(rentEntities);
    }
}