package com.library.user.poc.api.v1;

import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import com.library.user.poc.Main;
import com.library.user.poc.entity.UserEntity;
import com.library.user.poc.exception.UserAlreadyExistsException;
import com.library.user.poc.exception.UserNotFoundException;
import com.library.user.poc.service.UserService;
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
@Provider("user-service")
@PactFolder("../resources/pacts")
public class UserApiTest {

    @MockBean
    private UserService userService;

    @BeforeEach
    public void setupTestTarget(PactVerificationContext context) {
        context.setTarget(new HttpTestTarget("localhost", 8081, "/"));
    }

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    public void pactVerificationTestTemplate(PactVerificationContext context) {
        context.verifyInteraction();
    }

    @State("accept a new user")
    public void toCreateNewUser() {
        when(userService.insert(any(UserEntity.class))).thenReturn("03569852004");
    }

    @State("insert an existing user")
    public void toCreateAnExistingUser() {
        when(userService.insert(any(UserEntity.class)))
                .thenThrow(new UserAlreadyExistsException("User already exists."));
    }

    @State("get all users")
    public void toGetAllUsers() {
        List<UserEntity> userEntities = Arrays
                .asList(new UserEntity("John Cena", "51698532664", "12345678900"),
                        new UserEntity("Klark Kent", "55986525944", "98765432100"),
                        new UserEntity("Kakaroto", "45259563288", "98765432100"));
        when(userService.getAll()).thenReturn(userEntities);
    }

    @State("get one user by CPF")
    public void toGetUserByCpf() {
        when(userService.getByCpf("98765432100"))
                .thenReturn(new UserEntity("Klark Kent", "55986525944", "98765432100"));
    }

    @State("get an invalid user by CPF")
    public void toGetInvalidUser() {
        when(userService.getByCpf("98765432100"))
                .thenThrow(new UserNotFoundException("User not found."));
    }

    @State("update a user")
    public void toUpdateUserByCpf() {
        when(userService.update(eq("12345678900"), any(UserEntity.class)))
                .thenReturn(new UserEntity("Deivid Santos", "51958653203", "12345678900"));
    }

    @State("update a not found user")
    public void toUpdateExistingUser() {
        when(userService.update(eq("12345678900"), any(UserEntity.class)))
                .thenThrow(new UserAlreadyExistsException("User not found."));
    }
}

