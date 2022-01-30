package com.alpersayin.getir.service;

import com.alpersayin.getir.entity.BookEntity;
import com.alpersayin.getir.entity.OrderEntity;
import com.alpersayin.getir.entity.OrderItemEntity;
import com.alpersayin.getir.exception.ApiRequestException;
import com.alpersayin.getir.mapper.OrderMapper;
import com.alpersayin.getir.payload.request.ItemRequest;
import com.alpersayin.getir.payload.request.OrderRequest;
import com.alpersayin.getir.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final StockService stockService;

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public OrderEntity createOrder(OrderRequest orderRequest) {

        List<ItemRequest> items = orderRequest.getItems();
        boolean stocks = items.stream().allMatch(this::checkStockAvailability);

        List<OrderItemEntity> orderItems;
        if (stocks) {
            orderItems = items
                    .stream()
                    .map(orderMapper::mapToOrderItem)
                    .collect(Collectors.toList());
        } else {
            List<ItemRequest> unavailableList = items
                    .stream()
                    .filter(e -> !(checkStockAvailability(e)))
                    .collect(Collectors.toList());
            List<String> bookIds = unavailableList
                    .stream()
                    .map(ItemRequest::getBookId)
                    .collect(Collectors.toList());
            throw new ApiRequestException("Stock not available for Book Ids : " + Arrays.toString(bookIds.toArray()) );
        }

        return orderRepository.save(orderMapper.mapToOrder(orderRequest, orderItems));
    }

    private Boolean checkStockAvailability(ItemRequest itemRequest) {
        int stock = stockService.getStockByBookId(itemRequest.getBookId());
        int quantity = itemRequest.getQuantity();
        return stock >= quantity;
    }

}
