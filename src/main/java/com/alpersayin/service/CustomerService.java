package com.alpersayin.service;

import com.alpersayin.entity.CustomerEntity;

public interface CustomerService {
    CustomerEntity findByCustomerId(String id);
}
