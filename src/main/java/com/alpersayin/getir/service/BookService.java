package com.alpersayin.getir.service;

import com.alpersayin.getir.entity.BookEntity;
import com.alpersayin.getir.payload.request.BookRequest;

public interface BookService {
    BookEntity findByBookId(String id);
    BookEntity createBook(BookRequest bookRequest);
    BookEntity increaseStock(String id, Integer number);
    BookEntity decreaseStock(String id, Integer number);
}
