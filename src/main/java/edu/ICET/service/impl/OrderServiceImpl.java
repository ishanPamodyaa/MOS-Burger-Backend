package edu.ICET.service.impl;

import edu.ICET.dto.OrderDetailsDto;
import edu.ICET.dto.OrderDto;
import edu.ICET.entity.Order;
import edu.ICET.entity.OrderDetail;
import edu.ICET.entity.OrderDetails_pk;
import edu.ICET.entity.Product;
import edu.ICET.repocitory.OrderRepocitory;
import edu.ICET.repocitory.ProductRepocitory;
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
    final ProductRepocitory productRepocitory;
    final ModelMapper mapper;

    @Override
    public void addOrder(OrderDto orderDto) {
//        orderRepocitory.save(mapper.map(orderDto , Order.class));

    Order order = mapper.map(orderDto , Order.class);
    List<OrderDetail> orderDetail = new ArrayList<>();

    for(OrderDetailsDto orderDetailsDto : orderDto.getOrderDetails()){
//        OrderDetail orderDetailObj= new OrderDetail();
//
//        orderDetailObj.setOrderId(orderDto.getOrderId());
//        orderDetailObj.setProductId(orderDetailsDto.getProductId());

        Product product = productRepocitory.findByProductId(orderDetailsDto.getProductId())
                        .orElseThrow(() -> new RuntimeException("Product not found: " + orderDetailsDto.getProductId()));


        OrderDetails_pk orderDetailsPk = new OrderDetails_pk(orderDto.getOrderId(), orderDetailsDto.getProductId());

        OrderDetail orderDetailObj = new OrderDetail();

        orderDetailObj.setId(orderDetailsPk);
        orderDetailObj.setOrder(order);
        orderDetailObj.setProduct(product);
        orderDetailObj.setOrderQty(orderDetailsDto.getOrderQty());
        orderDetailObj.setDiscount(orderDetailsDto.getDiscount());
        orderDetailObj.setPrice(orderDetailsDto.getPrice());

        orderDetail.add(orderDetailObj);
    }

    order.setOrderDetail(orderDetail);
    orderRepocitory.save(order);
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
