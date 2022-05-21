package com.alpersayin.service;

import com.alpersayin.entity.BookEntity;
import com.alpersayin.payload.request.BookRequest;

public interface BookService {
    BookEntity findByBookId(String id);
    BookEntity createBook(BookRequest bookRequest);
    BookEntity stockIncrease(String id, Integer number);
    BookEntity stockDecrease(String id, Integer number);
}
