package com.alpersayin.controller;

import com.alpersayin.entity.OrderEntity;
import com.alpersayin.payload.request.LoginRequest;
import com.alpersayin.payload.request.RegisterRequest;
import com.alpersayin.payload.response.LoginResponse;
import com.alpersayin.payload.response.Response;
import com.alpersayin.service.CustomerServiceImpl;
import com.alpersayin.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerServiceImpl customerService;
    private final OrderService orderService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        return new ResponseEntity<>(customerService.login(loginRequest), OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Response> register(@Valid @RequestBody RegisterRequest registerRequest) {
        return new ResponseEntity<>(customerService.register(registerRequest), OK);
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<List<OrderEntity>> getOrdersByCustomerId(
            @PathVariable String id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {
        return new ResponseEntity<>(orderService.getOrdersByCustomerId(id, page, size), OK);
    }

}
