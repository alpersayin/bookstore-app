package com.alpersayin.getir.service;

import com.alpersayin.getir.entity.OrderEntity;

public interface PaymentService {
    boolean isPaid(OrderEntity order);
}
