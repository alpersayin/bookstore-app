package com.alpersayin.mapper;

import com.alpersayin.entity.BookEntity;
import com.alpersayin.entity.CustomerEntity;
import com.alpersayin.entity.OrderEntity;
import com.alpersayin.entity.OrderItemEntity;
import com.alpersayin.payload.request.ItemRequest;
import com.alpersayin.payload.request.OrderRequest;
import com.alpersayin.service.BookService;
import com.alpersayin.service.CustomerService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class OrderMapper {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private BookService bookService;

    @Mapping(target = "customer", expression = "java(getCustomer(orderRequest))")
    @Mapping(target = "orderDateTime", expression = "java(getLocalDateTime())")
    @Mapping(target = "orderItems", source = "items")
    public abstract OrderEntity mapToOrder(OrderRequest orderRequest, List<OrderItemEntity> items);

    LocalDateTime getLocalDateTime() {return LocalDateTime.now();}

    CustomerEntity getCustomer(OrderRequest orderRequest) {
        return customerService.findByCustomerId(orderRequest.getCustomerId());
    };

    @Mapping(target = "book", expression = "java(getBook(itemRequest))")
    public abstract OrderItemEntity mapToOrderItem(ItemRequest itemRequest);

    BookEntity getBook(ItemRequest itemRequest) {
        return bookService.findByBookId(itemRequest.getBookId());
    };

}
