package com.library.user.poc.repository;

import com.library.user.poc.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends MongoRepository<UserEntity, String> {
}
