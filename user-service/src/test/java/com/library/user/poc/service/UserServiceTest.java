package com.library.user.poc.service;

import com.library.user.poc.entity.UserEntity;
import com.library.user.poc.exception.UserAlreadyExistsException;
import com.library.user.poc.exception.UserNotFoundException;
import com.library.user.poc.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.beans.SamePropertyValuesAs.samePropertyValuesAs;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void insertOneUserSuccessTest() {
        UserEntity userEntity = buildUserEntity();
        when(userRepository.findById("123")).thenReturn(Optional.empty());
        when(userRepository.insert(userEntity)).thenReturn(userEntity);

        assertEquals(userEntity.getCpf(), userService.insert(userEntity));
    }

    @Test
    public void insertOneUserAlreadyExistsExceptionTest() {
        UserEntity userEntity = buildUserEntity();
        when(userRepository.findById("123")).thenReturn(Optional.of(buildUserEntity()));
        when(userRepository.insert(userEntity)).thenReturn(userEntity);

        UserAlreadyExistsException exception = assertThrows(UserAlreadyExistsException.class, () -> userService.insert(buildUserEntity()));
        assertEquals("User already exists.", exception.getMessage());
    }

    @Test
    public void getAllUsersSuccesTest() {
        when(userRepository.findAll()).thenReturn(buildUserList());
        List<UserEntity> users = userService.getAll();
        assertThat(users.get(0), samePropertyValuesAs(buildUserList().get(0)));
        assertThat(users.get(1), samePropertyValuesAs(buildUserList().get(1)));
        assertThat(users.get(2), samePropertyValuesAs(buildUserList().get(2)));
    }

    private List<UserEntity> buildUserList() {
        UserEntity userEntity1 = new UserEntity("Name Test1", "741", "123");
        UserEntity userEntity2 = new UserEntity("Name Test2", "852", "456");
        UserEntity userEntity3 = new UserEntity("Name Test3", "963", "789");
        return Arrays.asList(userEntity1, userEntity2, userEntity3);
    }

    @Test
    public void getUserByCpfSuccessTest() {
        UserEntity userEntity = buildUserEntity();
        when(userRepository.findById("123")).thenReturn(Optional.of(userEntity));

        assertThat(userEntity, samePropertyValuesAs(userService.getByCpf("123")));
    }

    @Test
    public void getUserByCpfNotFoundExceptionTest() {
        when(userRepository.findById("123")).thenReturn(Optional.empty());

        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> userService.getByCpf("123"));
        assertEquals("User not found.", exception.getMessage());
    }

    @Test
    public void updateUserSuccessTest() {
        UserEntity userEntity = buildUserEntity();
        when(userRepository.findById("123")).thenReturn(Optional.of(userEntity));
        when(userRepository.save(userEntity)).thenReturn(buildUserEntity());

        UserEntity user = userService.update("123", userEntity);
        assertThat(userEntity, samePropertyValuesAs(user));
    }

    @Test
    public void updateUserNotFoundExceptionTest() {
        when(userRepository.findById("123")).thenReturn(Optional.empty());

        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> userService.update("123", buildUserEntity()));
        assertEquals("User not found.", exception.getMessage());
    }

    private UserEntity buildUserEntity() {
        return new UserEntity("Deivid", "5189484196", "123");
    }

}