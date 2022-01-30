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
@Document(collection = "books")
public class BookEntity extends BaseEntity {
    private String title;
    private String author;
    private String publisher;
    private Double price;
    private Integer stock;
}
