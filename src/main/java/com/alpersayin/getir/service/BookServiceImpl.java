package com.alpersayin.getir.service;

import com.alpersayin.getir.entity.BookEntity;
import com.alpersayin.getir.payload.request.BookRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    @Override
    public BookEntity createBook(BookRequest bookRequest) {
        return null;
    }

    @Override
    public BookEntity increaseStock(String id, Integer number) {
        return null;
    }

    @Override
    public BookEntity decreaseStock(String id, Integer number) {
        return null;
    }
}
