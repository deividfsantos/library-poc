package com.library.rent.poc.repository;

import com.library.rent.poc.entity.RentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RentRepository extends MongoRepository<RentEntity, String> {

    List<RentEntity> findAllByCpf(String cpf);
}
