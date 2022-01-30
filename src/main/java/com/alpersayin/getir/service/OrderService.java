package com.alpersayin.getir.service;

import com.alpersayin.getir.entity.OrderEntity;
import com.alpersayin.getir.payload.request.OrderRequest;

public interface OrderService {
    OrderEntity createOrder(OrderRequest orderRequest);
}
