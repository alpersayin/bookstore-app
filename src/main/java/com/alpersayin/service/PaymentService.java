package com.alpersayin.service;

import com.alpersayin.entity.OrderEntity;

public interface PaymentService {
    boolean isPaid(OrderEntity order);
}
