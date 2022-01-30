package com.alpersayin.getir.service;

import com.alpersayin.getir.entity.UserEntity;

public interface UserEntityService {
    UserEntity findByUserName(String userName);
    Boolean existsByUserName(String userName);
    Boolean existsByEmail(String email);
}
