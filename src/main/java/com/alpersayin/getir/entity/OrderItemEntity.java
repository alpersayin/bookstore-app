package com.alpersayin.getir.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "order_items")
public class OrderItemEntity extends BaseEntity {
    private BookEntity book;
    private Integer quantity;
}
