package edu.ICET.service.impl;

import edu.ICET.dto.OrderDto;
import edu.ICET.entity.Order;
import edu.ICET.repocitory.OrderRepocitory;
import edu.ICET.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    final OrderRepocitory orderRepocitory;
    final ModelMapper mapper;

    @Override
    public void addOrder(OrderDto orderDto) {

        orderRepocitory.save(mapper.map(orderDto , Order.class));

    }

    @Override
    public List<OrderDto> getAllOrders() {
        List<OrderDto> orderResults = new ArrayList<>();
        List<Order> results = orderRepocitory.findAll();
        results.forEach(order -> {
            orderResults.add(mapper.map(order ,OrderDto.class));
        });
        return orderResults;
    }

    @Override
    public OrderDto getOrder(Integer id) {

        return mapper.map(orderRepocitory.findById(id) ,OrderDto.class) ;
    }

    @Override
    public OrderDto getOrderByName(String name) {
        return null;
    }

    @Override
    public OrderDto getOrderContactNumber(String contactNumber) {
        return null;
    }

    @Override
    public void deleteOrder(Integer id) {
        orderRepocitory.deleteById(id);
    }


}
