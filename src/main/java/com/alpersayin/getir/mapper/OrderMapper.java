package com.alpersayin.getir.mapper;

import com.alpersayin.getir.entity.BookEntity;
import com.alpersayin.getir.entity.CustomerEntity;
import com.alpersayin.getir.entity.OrderEntity;
import com.alpersayin.getir.entity.OrderItemEntity;
import com.alpersayin.getir.payload.request.ItemRequest;
import com.alpersayin.getir.payload.request.OrderRequest;
import com.alpersayin.getir.service.BookService;
import com.alpersayin.getir.service.CustomerService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ValueMapping;
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
