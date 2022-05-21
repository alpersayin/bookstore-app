package com.alpersayin.service;

import com.alpersayin.entity.OrderEntity;
import com.alpersayin.payload.request.OrderRequest;

import java.util.List;

public interface OrderService {
    OrderEntity createOrder(OrderRequest orderRequest);
    OrderEntity getOrder(String id);
    List<OrderEntity> getOrdersByCustomerId(String id, int page, int size);
    List<OrderEntity> getOrdersByDate(String startDate, String endDate);
}
