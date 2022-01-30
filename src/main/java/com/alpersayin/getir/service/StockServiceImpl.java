package com.alpersayin.getir.service;

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

    @Override
    public Boolean checkStockForOrder(Integer stock, Integer quantity) {
        return stock >= quantity;
    }

}
