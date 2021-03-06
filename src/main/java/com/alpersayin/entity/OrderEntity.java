package com.alpersayin.entity;

import com.alpersayin.entity.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "orders")
public class OrderEntity extends BaseEntity {
    private CustomerEntity customer;
    private LocalDateTime orderDateTime;
    private OrderStatus status = OrderStatus.CREATED;
    private List<OrderItemEntity> orderItems;
}
