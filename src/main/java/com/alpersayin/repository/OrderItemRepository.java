package com.alpersayin.repository;

import com.alpersayin.entity.OrderItemEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends MongoRepository<OrderItemEntity, String> {
}
