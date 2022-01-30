package com.alpersayin.getir.service;

import com.alpersayin.getir.entity.OrderEntity;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    /*
     *  The order payment will be considered as successfully paid.
     */
    @Override
    public boolean isPaid(OrderEntity order) {
        return true;
    }
}
