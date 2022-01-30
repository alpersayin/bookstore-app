package com.alpersayin.getir.service;

import com.alpersayin.getir.entity.OrderEntity;
import com.alpersayin.getir.payload.request.OrderRequest;

import java.util.List;

public interface OrderService {
    OrderEntity createOrder(OrderRequest orderRequest);
    OrderEntity getOrder(String id);
    List<OrderEntity> getOrdersByCustomerId(String id, int page, int size);
}
