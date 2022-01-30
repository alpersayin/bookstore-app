package com.alpersayin.getir.controller;

import com.alpersayin.getir.payload.request.LoginRequest;
import com.alpersayin.getir.payload.request.RegisterRequest;
import com.alpersayin.getir.payload.response.LoginResponse;
import com.alpersayin.getir.payload.response.Response;
import com.alpersayin.getir.service.CustomerServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerServiceImpl customerService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        return new ResponseEntity<>(customerService.login(loginRequest), OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<Response> register(@Valid @RequestBody RegisterRequest registerRequest) {
        return new ResponseEntity<>(customerService.register(registerRequest), OK);
    }

}
