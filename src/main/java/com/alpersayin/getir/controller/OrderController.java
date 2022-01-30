package com.alpersayin.getir.controller;

import com.alpersayin.getir.entity.OrderEntity;
import com.alpersayin.getir.payload.request.OrderRequest;
import com.alpersayin.getir.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;
    // persist new order
    @PostMapping("/create-order")
    public ResponseEntity<OrderEntity> createOrder(@Valid @RequestBody OrderRequest orderRequest){
        return new ResponseEntity<>(orderService.createOrder(orderRequest), CREATED);
    }
    // query order by id
    // list orders by date interval
}
