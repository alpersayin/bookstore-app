package com.alpersayin.getir.service;

import com.alpersayin.getir.entity.CustomerEntity;

public interface CustomerService {
    CustomerEntity findByCustomerId(String id);
}
