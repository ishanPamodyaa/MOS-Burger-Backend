package edu.ICET.service;

import edu.ICET.dto.OrderDto;

import java.util.List;

public interface OrderService {
    void addOrder(OrderDto orderDto);

    List<OrderDto> getAllOrders();

    OrderDto getOrder(String  orderId);

    OrderDto getOrderByName(String name);

    OrderDto getOrderContactNumber(String contactNumber);

    void deleteOrder(String orderId);

    byte[] generateInvoice(String orderId) throws Exception;
}
