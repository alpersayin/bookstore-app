package com.alpersayin.getir.service;

import com.alpersayin.getir.entity.CustomerEntity;
import com.alpersayin.getir.entity.OrderEntity;
import com.alpersayin.getir.entity.OrderItemEntity;
import com.alpersayin.getir.entity.enums.OrderStatus;
import com.alpersayin.getir.exception.ApiNotFoundException;
import com.alpersayin.getir.exception.ApiRequestException;
import com.alpersayin.getir.mapper.OrderMapper;
import com.alpersayin.getir.payload.request.ItemRequest;
import com.alpersayin.getir.payload.request.OrderRequest;
import com.alpersayin.getir.repository.OrderRepository;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final StockService stockService;
    private final CustomerService customerService;
    private final PaymentService paymentService;
    private final BookService bookService;

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    /*
     *  The order payment will be considered as successfully paid, If there is enough stock.
     */
    @Override
    @Transactional
    public OrderEntity createOrder(OrderRequest orderRequest) {

        List<ItemRequest> items = orderRequest.getItems();
        boolean stocks = items.stream().allMatch(this::checkStockAvailability);

        List<OrderItemEntity> orderItems;
        OrderEntity orderEntity;
        /*
         *  If there is enough stock, Order will be persist to DB, status as @CREATED.
         */
        if (stocks) {
            orderItems = getAvailableOrderItems(items);
            orderEntity = orderRepository.save(orderMapper.mapToOrder(orderRequest, orderItems));
        } else {
            List<ItemRequest> unavailableList = items
                    .stream()
                    .filter(e -> !(checkStockAvailability(e)))
                    .collect(Collectors.toList());
            List<String> bookIds = unavailableList
                    .stream()
                    .map(ItemRequest::getBookId)
                    .collect(Collectors.toList());
            throw new ApiRequestException("Stock not available for Book Ids: " + Arrays.toString(bookIds.toArray()));
        }

        /*
         *  Check if order is paid, then Order status will be updated as @PURCHASED.
         *  Then update stock of the each book that ordered.
         */
        OrderEntity paidOrder = new OrderEntity();

        if (isPurchased(orderEntity)) {
            paidOrder = purchaseOrder(orderEntity);
            updateStock(paidOrder);
        } else {
            throw new ApiRequestException("Payment for Order still not made for Order Id: " + paidOrder.getId());
        }
        return paidOrder;
    }

    @Transactional
    OrderEntity purchaseOrder(OrderEntity orderEntity) {
        orderEntity.setStatus(OrderStatus.PURCHASED);
        return orderRepository.save(orderEntity);
    }

    private Boolean isPurchased(OrderEntity orderEntity) {
        return paymentService.isPaid(orderEntity);
    }

    private void updateStock(OrderEntity orderEntity) {
        orderEntity.getOrderItems()
                .forEach(e -> bookService.decreaseStock(e.getBook().getId(), e.getQuantity()));
    }

    private List<OrderItemEntity> getAvailableOrderItems(List<ItemRequest> items) {
        List<ItemRequest> availableList = items
                .stream()
                .filter(this::checkStockAvailability)
                .collect(Collectors.toList());
        return availableList
                .stream()
                .map(orderMapper::mapToOrderItem)
                .collect(Collectors.toList());
    }

    @Override
    public OrderEntity getOrder(String id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ApiNotFoundException("Order Id with: " + id + " not found"));
    }

    @Override
    public List<OrderEntity> getOrdersByCustomerId(String id, int page, int size) {
        CustomerEntity customer = customerService.findByCustomerId(id);
        Pageable paging = PageRequest.of(page, size);
        Page<OrderEntity> pageOrders = orderRepository.findByCustomer(customer, paging);
        return pageOrders.get().collect(Collectors.toList());
    }

    @Override
    public List<OrderEntity> getOrdersByDate(String startDate, String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);
        return orderRepository.findOrderEntitiesByOrderDateTimeBetween(start.atStartOfDay(), end.atStartOfDay());
    }

    private Boolean checkStockAvailability(ItemRequest itemRequest) {
        int stock = stockService.getStockByBookId(itemRequest.getBookId());
        int quantity = itemRequest.getQuantity();
        return stock >= quantity;
    }

}
