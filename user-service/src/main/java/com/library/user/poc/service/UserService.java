package com.library.user.poc.service;

import com.library.user.poc.entity.UserEntity;
import com.library.user.poc.exception.UserAlreadyExistsException;
import com.library.user.poc.exception.UserNotFoundException;
import com.library.user.poc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String insert(UserEntity user) {
        if (userRepository.findById(user.getCpf()).isPresent()) {
            throw new UserAlreadyExistsException("User already exists.");
        }

        UserEntity userEntity = userRepository.insert(user);
        return userEntity.getCpf();
    }

    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

    public UserEntity getByCpf(String cpf) {
        return userRepository.findById(cpf)
                .orElseThrow(() -> new UserNotFoundException("User not found."));
    }

    public UserEntity update(String cpf, UserEntity user) {
        return userRepository.findById(cpf)
                .map(userFinal -> userRepository.save(user))
                .orElseThrow(() -> new UserNotFoundException("User not found."));
    }
}
