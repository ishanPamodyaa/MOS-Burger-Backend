package edu.ICET.service;

import edu.ICET.dto.OrderDto;

import java.util.List;

public interface OrderService {
    void addOrder(OrderDto orderDto);

    List<OrderDto> getAllOrders();

    OrderDto getOrder(Integer id);

    OrderDto getOrderByName(String name);

    OrderDto getOrderContactNumber(String contactNumber);

    void deleteOrder(Integer id);
}
