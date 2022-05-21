package com.alpersayin.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StockServiceImpl implements StockService {

    private final BookService bookService;

    @Override
    public Integer getStockByBookId(String id) {
        return bookService.findByBookId(id).getStock();
    }

}
