package com.alpersayin.getir.service;

import com.alpersayin.getir.entity.UserEntity;
import com.alpersayin.getir.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserEntityServiceImpl implements UserEntityService {

    private final UserRepository userRepository;

    @Override
    public UserEntity findByUserName(String userName) {
        return userRepository.findByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException("Username with: " + userName + " not found."));
    }

}
