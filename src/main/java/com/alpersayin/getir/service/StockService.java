package com.alpersayin.getir.service;

public interface StockService {
    Integer getStockByBookId(String id);
    Boolean checkStockForOrder(Integer stock, Integer quantity);
}
