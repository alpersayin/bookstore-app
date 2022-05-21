package com.alpersayin.service;

import com.alpersayin.entity.UserEntity;

public interface UserEntityService {
    UserEntity findByUserName(String userName);
    Boolean existsByUserName(String userName);
    Boolean existsByEmail(String email);
}
