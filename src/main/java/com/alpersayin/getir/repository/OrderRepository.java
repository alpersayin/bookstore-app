package com.alpersayin.getir.repository;

import com.alpersayin.getir.entity.CustomerEntity;
import com.alpersayin.getir.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<OrderEntity, String> {
    Page<OrderEntity> findByCustomer(CustomerEntity customerEntity, Pageable pageable);
    List<OrderEntity> findOrderEntitiesByOrderDateTimeBetween(LocalDateTime startDate, LocalDateTime endDate);
}
