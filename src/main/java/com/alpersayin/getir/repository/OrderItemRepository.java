package com.alpersayin.getir.repository;

import com.alpersayin.getir.entity.OrderItemEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends MongoRepository<OrderItemEntity, String> {
}
