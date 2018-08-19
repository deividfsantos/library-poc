package com.library.user.poc.api.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.user.poc.entity.UserEntity;
import com.library.user.poc.input.UserInput;
import com.library.user.poc.output.CpfOutput;
import com.library.user.poc.output.UserOutput;
import com.library.user.poc.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("v1/users")
public class UserApi {

    private ObjectMapper objectMapper;
    private UserService userService;

    public UserApi(ObjectMapper objectMapper, UserService userService) {
        this.objectMapper = objectMapper;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> insertUser(@RequestBody @Valid UserInput userInput) {
        String cpf = userService.insert(objectMapper.convertValue(userInput, UserEntity.class));
        return ok(new CpfOutput(cpf));
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        List<UserEntity> users = userService.getAll();
        return ok(users
                .stream()
                .map(user -> objectMapper.convertValue(user, UserOutput.class))
                .collect(Collectors.toList()));
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<?> getUserByCpf(@PathVariable String cpf) {
        return ok(objectMapper.convertValue(userService.getByCpf(cpf), UserOutput.class));
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<?> updateUser(@RequestBody UserInput input, @PathVariable String cpf) {
        UserEntity user = userService.update(cpf, objectMapper.convertValue(input, UserEntity.class));
        return ok(objectMapper.convertValue(user, UserOutput.class));
    }
}
