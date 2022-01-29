package com.alpersayin.getir.repository;

import com.alpersayin.getir.entity.CustomerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<CustomerEntity, Integer> {
}
