package com.alpersayin.getir.service;

import com.alpersayin.getir.entity.UserEntity;
import com.alpersayin.getir.payload.request.LoginRequest;
import com.alpersayin.getir.payload.request.RegisterRequest;
import com.alpersayin.getir.payload.response.LoginResponse;
import com.alpersayin.getir.payload.response.Response;
import com.alpersayin.getir.repository.CustomerRepository;
import com.alpersayin.getir.repository.UserRepository;
import com.alpersayin.getir.security.JwtProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl  {

    private final UserEntityService userEntityService;

    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;

    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public LoginResponse login(LoginRequest loginRequest) {
        UserEntity user = userEntityService.findByUserName(loginRequest.getUserName());
        return null;
    }

    public Response register(RegisterRequest registerRequest) {
        return null;
    }


}
